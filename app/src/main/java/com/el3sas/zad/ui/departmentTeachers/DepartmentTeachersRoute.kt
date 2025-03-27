package com.el3sas.zad.ui.departmentTeachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DepartmentTeachersRoute(
    modifier: Modifier = Modifier,
    coordinator: DepartmentTeachersCoordinator = rememberDepartmentTeachersCoordinator(),
    onAction: (DepartmentTeachersAction) -> Unit,
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(DepartmentTeachersState.Idle)

    DepartmentTeachersScreen(
        modifier = modifier,
        state = uiState,
        onAction = {
            when (it) {
                is DepartmentTeachersAction.LoadTeachersForDepartment -> coordinator::handle
                DepartmentTeachersAction.OnBackClicked -> onAction(it)
                is DepartmentTeachersAction.OnSelectTeacher -> onAction(it)
            }
        },
    )
}
