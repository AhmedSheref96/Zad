package com.el3asas.zad.departments.departmentTeachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DepartmentTeachersRoute(
    modifier: Modifier,
    coordinator: DepartmentTeachersCoordinator = rememberDepartmentTeachersCoordinator(),
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(DepartmentTeachersState.Idle)

    DepartmentTeachersScreen(
        modifier = modifier,
        state = uiState,
        onAction = coordinator::handle,
    )
}
