package com.el3sas.zad.ui.courses.courseshome

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.el3asas.zad.domain.usecases.GetCourses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesHomeViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val getCourses: GetCourses,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<CoursesHomeState> =
            MutableStateFlow(CoursesHomeState.Idle)

        val stateFlow: StateFlow<CoursesHomeState> = _stateFlow.asStateFlow()

        fun handleAction(action: CoursesHomeAction) {
            when (action) {
                is CoursesHomeAction.OnCourseClicked -> {
                }

                is CoursesHomeAction.GetCourses -> {
                    viewModelScope.launch {
                        getCourses(action.teacherId).collect {
                            _stateFlow.value = CoursesHomeState.CoursesLoaded(it)
                        }
                    }
                }
            }
        }
    }
