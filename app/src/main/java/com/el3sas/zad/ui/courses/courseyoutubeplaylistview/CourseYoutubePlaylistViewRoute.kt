package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.el3asas.zad.domain.models.CourseModel

@Composable
fun CourseYoutubePlaylistViewRoute(
    modifier: Modifier = Modifier,
    coordinator: CourseYoutubePlaylistViewCoordinator = rememberCourseYoutubePlaylistViewCoordinator(),
    courseModel: CourseModel? = null,
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actionsHandler: (CourseYoutubePlaylistViewAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CourseYoutubePlaylistViewScreen(
        modifier = modifier,
        state = uiState,
        onAction = actionsHandler,
        playlistDataPager = coordinator.viewModel.playlistDataPager,
        onVideoReadToPlay = {
//            val state = coordinator.viewModel.stateFlow.value
//            coordinator.viewModel.updateState(
//                state.copy(
//                    isLoadingVideo = false,
//                ),
//            )
        },
    )
}
