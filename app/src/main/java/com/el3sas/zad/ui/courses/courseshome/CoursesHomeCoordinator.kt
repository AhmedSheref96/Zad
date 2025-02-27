package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class CoursesHomeCoordinator(
    val viewModel: CoursesHomeViewModel,
) {
    val screenStateFlow = viewModel.stateFlow

    fun handle(action: CoursesHomeAction) {
        viewModel.handleAction(action)
    }
}

@Composable
fun rememberCoursesHomeCoordinator(viewModel: CoursesHomeViewModel = hiltViewModel()): CoursesHomeCoordinator =
    remember(viewModel) {
        CoursesHomeCoordinator(
            viewModel = viewModel,
        )
    }
