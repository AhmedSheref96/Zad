package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.models.PlaylistItem

/**
 * UI State that represents CourseYoutubePlaylistViewScreen
 **/
data class CourseYoutubePlaylistViewState(
    val courseModel: CourseModel,
    val playlistItems: List<PlaylistItem> = emptyList(),
    val isLoadingVideo: Boolean = true,
)

/**
 * CourseYoutubePlaylistView Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CourseYoutubePlaylistViewAction {
    data object OnClick : CourseYoutubePlaylistViewAction
}

/**
 * PreviewParameter Provider for CourseYoutubePlaylistViewScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class CourseYoutubePlaylistViewStatePreviewParameterProvider : PreviewParameterProvider<CourseYoutubePlaylistViewState> {
    override val values: Sequence<CourseYoutubePlaylistViewState>
        get() =
            sequenceOf()
}
