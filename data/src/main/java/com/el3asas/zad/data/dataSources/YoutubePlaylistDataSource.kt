package com.el3asas.zad.data.dataSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.el3asas.zad.data.services.YoutubeService
import com.el3asas.zad.domain.models.PlaylistItem
import javax.inject.Inject

class YoutubePlaylistDataSource
    @Inject
    constructor(
        private val playlistId: String,
        private val youtubeService: YoutubeService,
    ) : PagingSource<String, PlaylistItem>() {
        override fun getRefreshKey(state: PagingState<String, PlaylistItem>): String? = null

        override suspend fun load(params: LoadParams<String>): LoadResult<String, PlaylistItem> =
            try {
                val response =
                    youtubeService.getYoutubePlaylistData(
                        playlistId = playlistId,
                        pageToken = params.key,
                    )
                val data = response.items?.filterNotNull() ?: emptyList()
                LoadResult.Page(
                    prevKey = response.prevPageToken,
                    nextKey = response.nextPageToken,
                    data = data,
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
    }
