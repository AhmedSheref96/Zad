package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.PlaylistItem
import com.el3sas.zad.components.YoutubePlayListView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CourseYoutubePlaylistViewScreen(
    modifier: Modifier = Modifier,
    state: CourseYoutubePlaylistViewState,
    onVideoReadToPlay: () -> Unit = {},
    onAction: (CourseYoutubePlaylistViewAction) -> Unit,
    playlistDataPager: Flow<PagingData<PlaylistItem>> = flow { },
) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        var youTubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
        YoutubePlayListView(
            playlistId = state.courseModel.courseYoutubeUrl,
            onReady = { player ->
                youTubePlayer = player
            },
        )

        PlaylistView(playlistDataPager = playlistDataPager) {
            youTubePlayer?.playVideoAt(it)
        }
    }
}

@Composable
fun PlaylistView(
    playlistDataPager: Flow<PagingData<PlaylistItem>>,
    onItemClick: (Int) -> Unit,
) {
    var selectedVideo by remember { mutableIntStateOf(0) }
    val playlistItems = playlistDataPager.collectAsLazyPagingItems()
    LazyColumn {
        items(count = playlistItems.itemCount) {
            PlaylistItemView(
                item = playlistItems[it]!!,
                it == selectedVideo,
                onItemClick = { position, id ->
                    onItemClick(position)
                    selectedVideo = position
                },
            )
        }
    }
}

@Composable
fun PlaylistItemView(
    item: PlaylistItem,
    isSelected: Boolean = false,
    onItemClick: (Int, String) -> Unit,
) {
    val background = if (isSelected) Color.Gray.copy(alpha = .2f) else Color.Transparent
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item.snippet.position, item.id) }
                .background(color = background, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val images = item.snippet.thumbnails
        val image = images?.jsonMemberDefault
        AsyncImage(
            model = image?.url,
            contentDescription = null,
            modifier =
                Modifier.size(
                    height = (image?.height ?: 80).dp,
                    width = (image?.width ?: 110).dp,
                ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = item.snippet.title)
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
