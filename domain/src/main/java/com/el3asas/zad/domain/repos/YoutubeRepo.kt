package com.el3asas.zad.domain.repos

import androidx.paging.Pager
import com.el3asas.zad.domain.models.PlaylistItem

interface YoutubeRepo {
    fun getYoutubePlaylistData(playlistId: String): Pager<String, PlaylistItem>
}
