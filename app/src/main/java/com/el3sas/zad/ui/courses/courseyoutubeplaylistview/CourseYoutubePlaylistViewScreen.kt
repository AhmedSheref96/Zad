package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.el3asas.zad.domain.models.PlaylistItem
import com.el3asas.zad.systemdesign.components.YoutubePlayListView
import com.el3asas.zad.systemdesign.components.YoutubePlaylistItemView
import com.el3sas.zad.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Composable
fun CourseYoutubePlaylistViewScreen(
    modifier: Modifier = Modifier,
    state: CourseYoutubePlaylistViewState,
    onAction: (CourseYoutubePlaylistViewAction) -> Unit,
    playlistDataPager: Flow<PagingData<PlaylistItem>> = flow { },
) {
    var youTubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }

    PlaylistView(
        modifier = modifier.fillMaxSize(),
        playlistDataPager = playlistDataPager,
        onItemClick = { position ->
            youTubePlayer?.playVideoAt(position)
        },
    ) {
        YoutubePlayListView(
            modifier = Modifier,
            playlistId = state.courseModel.courseYoutubeUrl,
            onReady = { player ->
                youTubePlayer = player
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistView(
    modifier: Modifier = Modifier,
    playlistDataPager: Flow<PagingData<PlaylistItem>>,
    onItemClick: (Int) -> Unit,
    mainContent: @Composable () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = {
            var selectedVideo by remember { mutableIntStateOf(0) }
            val playlistItems = playlistDataPager.collectAsLazyPagingItems()
            LazyColumn {
                items(count = playlistItems.itemCount, key = { playlistItems[it]!!.id }) { index ->
                    playlistItems[index]?.let { item ->
                        YoutubePlaylistItemView(
                            item = item,
                            isSelected = index == selectedVideo,
                            onItemClick = { position, _ ->
                                scope.launch { sheetState.hide() }
                                onItemClick(position)
                                selectedVideo = position
                            },
                        )
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier.fillMaxSize(),
        ) {
            mainContent()

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors =
                    ButtonDefaults.buttonColors(containerColor = Color.Gray),
                onClick = { scope.launch { sheetState.expand() } },
            ) {
                Text(stringResource(R.string.course_playlist))
            }
        }
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
