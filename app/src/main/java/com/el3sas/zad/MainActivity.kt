package com.el3sas.zad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.systemdesign.theme.ZadTheme
import com.el3sas.zad.ui.Destinations
import com.el3sas.zad.ui.courses.courseshome.CoursesHomeAction
import com.el3sas.zad.ui.courses.courseshome.CoursesHomeRoute
import com.el3sas.zad.ui.courses.courseyoutubeplaylistview.CourseYoutubePlaylistViewRoute
import com.el3sas.zad.ui.departmentTeachers.DepartmentTeachersAction
import com.el3sas.zad.ui.departmentTeachers.DepartmentTeachersRoute
import com.el3sas.zad.ui.departmentsHome.DepartmentsAction
import com.el3sas.zad.ui.departmentsHome.DepartmentsRoute
import com.el3sas.zad.ui.departmentsHome.rememberDepartmentsCoordinator
import com.el3sas.zad.utils.navType
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZadTheme {
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        Timber.d(
            "destination changed ${destination.route}\n" +
                "destination $destination\n" +
                "arguments $arguments\n",
        )
    }

    NavHost(
        startDestination = Destinations.DepartmentsList,
        navController = navController,
    ) {
        composable<Destinations.YoutubePlaylist>(
            typeMap =
                mapOf(
                    typeOf<CourseModel>() to navType<CourseModel>(serializer = CourseModel.serializer()),
                ),
        ) {
            val args = it.toRoute<Destinations.YoutubePlaylist>()
            CourseYoutubePlaylistViewRoute(
                courseModel = args.courseModel,
            )
        }

        composable<Destinations.DepartmentTeachers>(
            typeMap =
                mapOf(
                    typeOf<DepartmentModel>() to navType<DepartmentModel>(serializer = DepartmentModel.serializer()),
                ),
        ) {
            DepartmentTeachersRoute(
                modifier = modifier,
                onAction = {
                    when (it) {
                        DepartmentTeachersAction.OnBackClicked -> navController.popBackStack()
                        is DepartmentTeachersAction.OnSelectTeacher ->
                            navController.navigate(
                                Destinations.Courses(it.teacherModel.id),
                            )

                        else -> Unit
                    }
                },
            )
        }

        composable<Destinations.DepartmentsList> {
            DepartmentsRoute(
                modifier = modifier,
                coordinator =
                    rememberDepartmentsCoordinator(
                        viewModel = hiltViewModel(),
                        action = {
                            when (it) {
                                is DepartmentsAction.OnDepartmentCardClicked -> {
                                    navController.navigate(Destinations.DepartmentTeachers(it.departmentModel))
                                }
                            }
                        },
                    ),
            )
        }

        composable<Destinations.Courses> {
            val args = it.toRoute<Destinations.Courses>()
            CoursesHomeRoute(
                teacherId = args.teacherId,
            ) { action ->
                when (action) {
                    is CoursesHomeAction.OnCourseClicked ->
                        navController.navigate(Destinations.YoutubePlaylist(action.courseModel))

                    else -> Unit
                }
            }
        }
    }
}
