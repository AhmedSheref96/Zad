package com.el3asas.zad.departments.departmentsHome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import javax.inject.Inject

class
DepartmentsCoordinator
    @Inject
    constructor(
        val viewModel: DepartmentsViewModel,
        val action: (DepartmentsAction) -> Unit = {},
    ) {
        val screenStateFlow = viewModel.stateFlow

        fun handleActions(action: DepartmentsAction) = action(action)
    }

@Composable
fun rememberDepartmentsCoordinator(
    viewModel: DepartmentsViewModel,
    action: (DepartmentsAction) -> Unit = {},
): DepartmentsCoordinator =
    remember(viewModel) {
        DepartmentsCoordinator(
            viewModel = viewModel,
            action = action,
        )
    }
