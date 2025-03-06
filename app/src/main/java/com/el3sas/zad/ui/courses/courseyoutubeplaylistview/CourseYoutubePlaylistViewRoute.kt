package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.el3asas.zad.domain.models.CourseModel

@Composable
fun CourseYoutubePlaylistViewRoute(
    coordinator: CourseYoutubePlaylistViewCoordinator = rememberCourseYoutubePlaylistViewCoordinator(),
    courseModel: CourseModel,
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(
        CourseYoutubePlaylistViewState(courseModel = courseModel),
    )

    // UI Actions
    val actionsHandler: (CourseYoutubePlaylistViewAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CourseYoutubePlaylistViewScreen(
        state = uiState,
        onAction = actionsHandler,
    )
}
