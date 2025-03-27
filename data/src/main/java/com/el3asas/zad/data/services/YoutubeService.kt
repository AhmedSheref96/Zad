package com.el3asas.zad.data.services

import com.el3asas.zad.domain.models.YoutubePlaylistDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("playlistItems")
    suspend fun getYoutubePlaylistData(
        @Query("playlistId") playlistId: String,
        @Query("part") part: String = "snippet",
        @Query("pageToken") pageToken: String? = null,
    ): YoutubePlaylistDataResponse
}
