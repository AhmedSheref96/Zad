package com.el3asas.zad.courses.courseTeachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CourseTeachersRoute(
    modifier: Modifier,
    coordinator: CourseTeachersCoordinator = rememberCourseTeachersCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CourseTeachersState.Idle)

    // UI Actions
    val actionsHandler: (CourseTeachersAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CourseTeachersScreen(
        state = uiState,
        onAction = actionsHandler,
    )
}
