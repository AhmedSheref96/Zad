package com.el3asas.zad.courses.courseTeachers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CourseTeachersViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<CourseTeachersState> =
            MutableStateFlow(CourseTeachersState.Idle)

        val stateFlow: StateFlow<CourseTeachersState> = _stateFlow.asStateFlow()
    }
