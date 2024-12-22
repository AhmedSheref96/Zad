package com.el3sas.zad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.el3asas.zad.courses.courseTeachers.CourseTeachersRoute
import com.el3asas.zad.courses.coursesHome.CoursesAction
import com.el3asas.zad.courses.coursesHome.CoursesDestinations
import com.el3asas.zad.courses.coursesHome.CoursesRoute
import com.el3asas.zad.courses.coursesHome.rememberCoursesCoordinator
import com.el3asas.zad.systemdesign.theme.ZadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZadTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Select Course To Begin Learning..")
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("Select Course To Begin Learning..")
                                }
                            },
                            expandedHeight = TopAppBarDefaults.LargeAppBarExpandedHeight,
                            scrollBehavior = scrollBehavior,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
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
    NavHost(
        startDestination = CoursesDestinations.List,
        navController = navController,
    ) {
        composable<CoursesDestinations.List> {
            CoursesRoute(
                modifier = modifier,
                coordinator =
                    rememberCoursesCoordinator(
                        viewModel = hiltViewModel(),
                        action = { action ->
                            when (action) {
                                is CoursesAction.OnCourseCardClicked -> {
                                    navController.navigate(CoursesDestinations.CourseTeachers)
                                }

                                is CoursesAction.OnAddCourseToFavorite -> {
                                    // TODO: favorite course
                                }
                            }
                        },
                    ),
            )
        }

        composable<CoursesDestinations.CourseTeachers> {
            CourseTeachersRoute(modifier = modifier)
        }

        composable<CoursesDestinations.CourseDetails> {
        }
    }
}
