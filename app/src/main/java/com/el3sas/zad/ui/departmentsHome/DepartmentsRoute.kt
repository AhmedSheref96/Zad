package com.el3sas.zad.ui.departmentsHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DepartmentsRoute(
    modifier: Modifier = Modifier,
    coordinator: DepartmentsCoordinator,
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(DepartmentsState.Idle)

    CoursesScreen(
        modifier = modifier,
        state = uiState,
        onAction = coordinator::handleActions,
    )
}
