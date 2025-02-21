package com.el3sas.zad.ui.departmentsHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.el3asas.zad.domain.usecases.GetDepartments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsViewModel
    @Inject
    constructor(
        private val getDepartments: GetDepartments,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<DepartmentsState> = MutableStateFlow(DepartmentsState.Idle)

        val stateFlow: StateFlow<DepartmentsState> = _stateFlow.asStateFlow()

        init {
            viewModelScope.launch {
                _stateFlow.value = DepartmentsState.Loading

                getDepartments().collect { courses ->
                    _stateFlow.update { state ->
                        DepartmentsState.LoadDepartments(courses)
                    }
                }
            }
        }
    }
