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
import com.el3asas.zad.courses.courseTeachers.CourseTeachersRoute
import com.el3asas.zad.courses.coursesHome.CoursesAction
import com.el3asas.zad.courses.coursesHome.CoursesDestinations
import com.el3asas.zad.courses.coursesHome.CoursesRoute
import com.el3asas.zad.courses.coursesHome.rememberCoursesCoordinator
import com.el3asas.zad.systemdesign.theme.ZadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZadTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
