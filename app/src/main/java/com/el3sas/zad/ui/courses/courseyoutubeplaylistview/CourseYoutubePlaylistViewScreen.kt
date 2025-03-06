package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.el3sas.zad.components.YoutubePlayListView

@Composable
fun CourseYoutubePlaylistViewScreen(
    state: CourseYoutubePlaylistViewState,
    onAction: (CourseYoutubePlaylistViewAction) -> Unit,
) {
    Column {
        YoutubePlayListView(
            modifier =
                Modifier
                    .fillMaxSize(),
            playlistId = state.courseModel!!.courseYoutubeUrl,
        )
    }
}

@Composable
@Preview(name = "CourseYoutubePlaylistView")
private fun CourseYoutubePlaylistViewScreenPreview(
    @PreviewParameter(CourseYoutubePlaylistViewStatePreviewParameterProvider::class)
    state: CourseYoutubePlaylistViewState,
) {
    CourseYoutubePlaylistViewScreen(
        state = state,
        onAction = {},
    )
}
