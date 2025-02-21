package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoursesHomeRoute(coordinator: CoursesHomeCoordinator = rememberCoursesHomeCoordinator()) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CoursesHomeState())

    // UI Actions
    val actionsHandler: (CoursesHomeAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CoursesHomeScreen(
        state = uiState,
        onAction = actionsHandler,
    )
}
