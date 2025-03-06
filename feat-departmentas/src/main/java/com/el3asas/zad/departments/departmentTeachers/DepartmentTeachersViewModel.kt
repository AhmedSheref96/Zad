package com.el3asas.zad.departments.departmentTeachers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.repos.TeachersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentTeachersViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val repo: TeachersRepo,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<DepartmentTeachersState> =
            MutableStateFlow(DepartmentTeachersState.Idle)

        val stateFlow: StateFlow<DepartmentTeachersState> = _stateFlow.asStateFlow()

        fun loadTeachers(id: String) {
            viewModelScope.launch {
                repo
                    .getTeachersForDepartment(DepartmentModel(id = id))
                    .onStart {
                        _stateFlow.update { state ->
                            DepartmentTeachersState.Loading
                        }
                    }.collect { teachers ->
                        _stateFlow.update { state ->
                            DepartmentTeachersState.LoadTeachers(teachers = teachers)
                        }
                    }
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
