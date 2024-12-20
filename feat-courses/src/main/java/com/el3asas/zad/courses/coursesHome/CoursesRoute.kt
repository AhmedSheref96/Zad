package com.el3asas.zad.courses.coursesHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoursesRoute(
    modifier: Modifier = Modifier,
    coordinator: CoursesCoordinator,
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CoursesState.Idle)

    CoursesScreen(
        modifier = modifier,
        state = uiState,
        onAction = coordinator::handleActions,
    )
}
