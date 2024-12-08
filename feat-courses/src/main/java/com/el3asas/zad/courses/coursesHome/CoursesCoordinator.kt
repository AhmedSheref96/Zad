package com.el3asas.zad.courses.coursesHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class CoursesCoordinator(
    val viewModel: CoursesViewModel,
) {
    val screenStateFlow = viewModel.stateFlow

    fun handle(action: CoursesAction) {
        when (action) {
            is CoursesAction.OnCourseCardClicked -> {
                viewModel.openCourseTeachers(action.courseModel)
            }

            is CoursesAction.OnAddCourseToFavorite -> {
                viewModel.openCourseTeachers(action.courseModel)
            }
        }
    }
}

@Composable
fun rememberCoursesCoordinator(viewModel: CoursesViewModel = hiltViewModel()): CoursesCoordinator =
    remember(viewModel) {
        CoursesCoordinator(
            viewModel = viewModel,
        )
    }
