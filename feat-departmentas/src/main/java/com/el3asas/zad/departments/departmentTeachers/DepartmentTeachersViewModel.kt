package com.el3asas.zad.departments.departmentTeachers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.el3asas.zad.domain.models.DepartmentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DepartmentTeachersViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<DepartmentTeachersState> =
            MutableStateFlow(DepartmentTeachersState.Idle)

        val stateFlow: StateFlow<DepartmentTeachersState> = _stateFlow.asStateFlow()

        fun loadTeachers(id: String) {
            _stateFlow.update { state ->
                DepartmentTeachersState.LoadTeachers(emptyList())
            }
        }

        init {
            savedStateHandle
                .get<DepartmentModel>("departmentModel")
                ?.id
                ?.let {
                    loadTeachers(it)
                }
        }
    }
