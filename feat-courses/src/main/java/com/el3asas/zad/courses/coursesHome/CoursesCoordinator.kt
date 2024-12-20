package com.el3asas.zad.courses.coursesHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import javax.inject.Inject

class
CoursesCoordinator
    @Inject
    constructor(
        val viewModel: CoursesViewModel,
        val action: (CoursesAction) -> Unit = {},
    ) {
        val screenStateFlow = viewModel.stateFlow

        fun handleActions(action: CoursesAction) = action(action)
    }

@Composable
fun rememberCoursesCoordinator(
    viewModel: CoursesViewModel,
    action: (CoursesAction) -> Unit = {},
): CoursesCoordinator =
    remember(viewModel) {
        CoursesCoordinator(
            viewModel = viewModel,
            action = action,
        )
    }
