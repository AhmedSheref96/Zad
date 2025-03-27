package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.el3asas.zad.domain.models.CourseModel

/**
 * UI State that represents CoursesHomeScreen
 **/
sealed class CoursesHomeState {
    data object Loading : CoursesHomeState()

    data object Error : CoursesHomeState()

    data object Idle : CoursesHomeState()

    data class CoursesLoaded(
        val courses: List<CourseModel>,
    ) : CoursesHomeState()
}

/**
 * CoursesHome Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CoursesHomeAction {
    data class OnCourseClicked(
        val courseModel: CourseModel,
    ) : CoursesHomeAction

    data class GetCourses(
        val teacherId: String,
    ) : CoursesHomeAction
}

/**
 * PreviewParameter Provider for CoursesHomeScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class CoursesHomeStatePreviewParameterProvider : PreviewParameterProvider<CoursesHomeState> {
    override val values: Sequence<CoursesHomeState>
        get() =
            sequenceOf(
                CoursesHomeState.Loading,
                CoursesHomeState.Error,
                CoursesHomeState.Idle,
                CoursesHomeState.CoursesLoaded(
                    listOf(
                        CourseModel(
                            id = "1",
                            title = "Course 1",
                            description = "Course 1 Description",
                            imageUrl = "https://via.placeholder.com/150",
                            teachers = emptyList(),
                            properties = emptyList(),
                            courseYoutubeUrl = "",
                        ),
                        CourseModel(
                            id = "2",
                            title = "Course 1",
                            description = "Course 1 Description",
                            imageUrl = "https://via.placeholder.com/150",
                            teachers = emptyList(),
                            properties = emptyList(),
                            courseYoutubeUrl = "",
                        ),
                        CourseModel(
                            id = "3",
                            title = "Course 1",
                            description = "Course 1 Description",
                            imageUrl = "https://via.placeholder.com/150",
                            teachers = emptyList(),
                            properties = emptyList(),
                            courseYoutubeUrl = "",
                        ),
                        CourseModel(
                            id = "4",
                            title = "Course 1",
                            description = "Course 1 Description",
                            imageUrl = "https://via.placeholder.com/150",
                            teachers = emptyList(),
                            properties = emptyList(),
                            courseYoutubeUrl = "",
                        ),
                    ),
                ),
            )
}
