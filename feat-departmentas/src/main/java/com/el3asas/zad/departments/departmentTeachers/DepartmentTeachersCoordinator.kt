package com.el3asas.zad.departments.departmentTeachers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import javax.inject.Inject

class DepartmentTeachersCoordinator
    @Inject
    constructor(
        val viewModel: DepartmentTeachersViewModel,
    ) {
        val screenStateFlow = viewModel.stateFlow

        fun handle(action: DepartmentTeachersAction) {
            when (action) {
                DepartmentTeachersAction.OnSelectTeacher -> {
                }

                is DepartmentTeachersAction.LoadTeachersForDepartment -> {
                    viewModel.loadTeachers(action.model.id)
                }
            }
        }
    }

@Composable
fun rememberDepartmentTeachersCoordinator(viewModel: DepartmentTeachersViewModel = hiltViewModel()): DepartmentTeachersCoordinator =
    remember(viewModel) {
        DepartmentTeachersCoordinator(
            viewModel = viewModel,
        )
    }
