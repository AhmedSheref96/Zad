package com.el3asas.zad.courses.coursesHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.el3asas.zad.courses.navigation.INavigationListener
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.usecases.GetCourses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel
    @Inject
    constructor(
        private val getCourses: GetCourses,
        private val navigator: INavigationListener,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<CoursesState> = MutableStateFlow(CoursesState.Idle)

        val stateFlow: StateFlow<CoursesState> = _stateFlow.asStateFlow()

        fun openCourseTeachers(courseModel: CourseModel) {
            navigator.onCourseCardClicked(courseModel)
        }

        init {
            viewModelScope.launch {
                _stateFlow.value = CoursesState.Loading
                val courses = getCourses()
                _stateFlow.update { state ->
                    CoursesState.LoadCourses(courses)
                }
            }
        }
    }
