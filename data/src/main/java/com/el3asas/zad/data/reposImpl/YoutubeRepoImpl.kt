package com.el3asas.zad.data.reposImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.el3asas.zad.data.dataSources.YoutubePlaylistDataSource
import com.el3asas.zad.data.services.YoutubeService
import com.el3asas.zad.domain.models.PlaylistItem
import com.el3asas.zad.domain.repos.YoutubeRepo
import javax.inject.Inject

class YoutubeRepoImpl
    @Inject
    constructor(
        private val youtubeService: YoutubeService,
    ) : YoutubeRepo {
        override fun getYoutubePlaylistData(playlistId: String): Pager<String, PlaylistItem> =
            Pager(
                config = PagingConfig(pageSize = 7),
            ) {
                YoutubePlaylistDataSource(playlistId = playlistId, youtubeService = youtubeService)
            }
    }
