package com.el3asas.zad.courses.coursesHome

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.el3asas.zad.domain.models.CourseModel
import com.kiwi.navigationcompose.typed.Destination
import kotlinx.serialization.Serializable

@Serializable
object CoursesDestination : Destination

sealed class CoursesState {
    data object Idle : CoursesState()

    data object Loading : CoursesState()

    data class LoadCourses(
        val courses: List<CourseModel> = emptyList(),
    ) : CoursesState()

    data class OpenCourseTeachers(
        val courseModel: CourseModel,
    ) : CoursesState()

    data class AddCourseToFavorite(
        val courseModel: CourseModel,
    ) : CoursesState()

    data class Error(
        val message: String,
    ) : CoursesState()
}

sealed interface CoursesAction {
    data class OnCourseCardClicked(
        val courseModel: CourseModel,
    ) : CoursesAction

    data class OnAddCourseToFavorite(
        val courseModel: CourseModel,
    ) : CoursesAction
}

class CoursesStatePreviewParameterProvider : PreviewParameterProvider<CoursesState> {
    override val values: Sequence<CoursesState>
        get() = sequenceOf()
}
