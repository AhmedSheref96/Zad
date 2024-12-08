package com.el3asas.zad.courses.coursesHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoursesRoute(
    modifier: Modifier = Modifier,
    coordinator: CoursesCoordinator = rememberCoursesCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CoursesState.Idle)

    // UI Actions
    val actionsHandler: (CoursesAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CoursesScreen(
        modifier = modifier,
        state = uiState,
        onAction = actionsHandler,
    )
}
