package com.el3asas.zad.courses.courseTeachers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.el3asas.zad.domain.models.TeacherModel

sealed class CourseTeachersState {
    data object Idle : CourseTeachersState()

    data object Loading : CourseTeachersState()

    data class Error(
        val message: String,
    ) : CourseTeachersState()

    data class LoadTeachers(
        val teachers: List<TeacherModel>,
    ) : CourseTeachersState()
}

sealed interface CourseTeachersAction {
    data object OnSelectTeacher : CourseTeachersAction
}

class CourseTeachersStatePreviewParameterProvider : PreviewParameterProvider<CourseTeachersState> {
    override val values: Sequence<CourseTeachersState>
        get() =
            sequenceOf()
}
