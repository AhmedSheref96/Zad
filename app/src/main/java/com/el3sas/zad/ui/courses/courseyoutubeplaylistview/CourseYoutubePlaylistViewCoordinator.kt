package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class CourseYoutubePlaylistViewCoordinator(
    val viewModel: CourseYoutubePlaylistViewViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: CourseYoutubePlaylistViewAction) {
        when (action) {
            CourseYoutubePlaylistViewAction.OnClick -> { /* Handle action */
            }
        }
    }


}

@Composable
fun rememberCourseYoutubePlaylistViewCoordinator(
    viewModel: CourseYoutubePlaylistViewViewModel = hiltViewModel()
): CourseYoutubePlaylistViewCoordinator {
    return remember(viewModel) {
        CourseYoutubePlaylistViewCoordinator(
            viewModel = viewModel
        )
    }
}