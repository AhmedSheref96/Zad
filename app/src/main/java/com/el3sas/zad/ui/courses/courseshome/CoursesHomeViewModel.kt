package com.el3sas.zad.ui.courses.courseshome

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoursesHomeViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<CoursesHomeState> =
            MutableStateFlow(CoursesHomeState())

        val stateFlow: StateFlow<CoursesHomeState> = _stateFlow.asStateFlow()
    }
