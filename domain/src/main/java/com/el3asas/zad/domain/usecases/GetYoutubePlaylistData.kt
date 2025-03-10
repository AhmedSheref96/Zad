package com.el3asas.zad.domain.usecases

import androidx.paging.Pager
import com.el3asas.zad.domain.models.PlaylistItem
import com.el3asas.zad.domain.repos.YoutubeRepo
import javax.inject.Inject

class GetYoutubePlaylistData
    @Inject
    constructor(
        private val youtubeRepo: YoutubeRepo,
    ) {
        operator fun invoke(playlistId: String): Pager<String, PlaylistItem> = youtubeRepo.getYoutubePlaylistData(playlistId = playlistId)
    }
