package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoursesHomeRoute(
    modifier: Modifier = Modifier,
    teacherId: String,
    coordinator: CoursesHomeCoordinator = rememberCoursesHomeCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CoursesHomeState.Idle)

    // UI Actions
    val actionsHandler: (CoursesHomeAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    coordinator.handle(CoursesHomeAction.GetCourses(teacherId = teacherId))

    // UI Rendering
    CoursesHomeScreen(
        state = uiState,
        onAction = actionsHandler,
    )
}
