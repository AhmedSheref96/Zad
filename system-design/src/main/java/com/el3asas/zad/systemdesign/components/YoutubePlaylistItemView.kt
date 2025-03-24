package com.el3asas.zad.systemdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.PlaylistItem

@Composable
fun YoutubePlaylistItemView(
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
