package com.el3asas.zad.courses.courseTeachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import javax.inject.Inject

class CourseTeachersCoordinator
    @Inject
    constructor(
        val viewModel: CourseTeachersViewModel,
    ) {
        val screenStateFlow = viewModel.stateFlow

        fun handle(action: CourseTeachersAction) {
            when (action) {
                CourseTeachersAction.OnSelectTeacher -> {
                }
            }
        }
    }

@Composable
fun rememberCourseTeachersCoordinator(viewModel: CourseTeachersViewModel = hiltViewModel()): CourseTeachersCoordinator =
    remember(viewModel) {
        CourseTeachersCoordinator(
            viewModel = viewModel,
        )
    }
