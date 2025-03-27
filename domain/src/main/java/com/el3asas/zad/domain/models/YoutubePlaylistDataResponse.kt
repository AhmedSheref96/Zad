package com.el3asas.zad.domain.models

import com.google.gson.annotations.SerializedName

data class YoutubePlaylistDataResponse(
    @field:SerializedName("kind")
    val kind: String? = null,
    @field:SerializedName("nextPageToken")
    val nextPageToken: String? = null,
    @field:SerializedName("prevPageToken")
    val prevPageToken: String? = null,
    @field:SerializedName("pageInfo")
    val pageInfo: PageInfo? = null,
    @field:SerializedName("etag")
    val etag: String? = null,
    @field:SerializedName("items")
    val items: List<PlaylistItem?>? = null,
)

data class PageInfo(
    @field:SerializedName("totalResults")
    val totalResults: Int? = null,
    @field:SerializedName("resultsPerPage")
    val resultsPerPage: Int? = null,
)

data class ResourceId(
    @field:SerializedName("kind")
    val kind: String? = null,
    @field:SerializedName("videoId")
    val videoId: String? = null,
)

data class JsonMemberDefault(
    @field:SerializedName("width")
    val width: Int? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("height")
    val height: Int? = null,
)

data class Standard(
    @field:SerializedName("width")
    val width: Int? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("height")
    val height: Int? = null,
)

data class High(
    @field:SerializedName("width")
    val width: Int? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("height")
    val height: Int? = null,
)

data class Thumbnails(
    @field:SerializedName("standard")
    val standard: Standard? = null,
    @field:SerializedName("default")
    val jsonMemberDefault: JsonMemberDefault? = null,
    @field:SerializedName("high")
    val high: High? = null,
    @field:SerializedName("maxres")
    val maxres: Maxres? = null,
    @field:SerializedName("medium")
    val medium: Medium? = null,
)

data class Snippet(
    @field:SerializedName("playlistId")
    val playlistId: String? = null,
    @field:SerializedName("resourceId")
    val resourceId: ResourceId? = null,
    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("position")
    val position: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("thumbnails")
    val thumbnails: Thumbnails? = null,
    @field:SerializedName("channelId")
    val channelId: String? = null,
    @field:SerializedName("videoOwnerChannelId")
    val videoOwnerChannelId: String? = null,
    @field:SerializedName("channelTitle")
    val channelTitle: String? = null,
    @field:SerializedName("videoOwnerChannelTitle")
    val videoOwnerChannelTitle: String? = null,
)

data class Maxres(
    @field:SerializedName("width")
    val width: Int? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("height")
    val height: Int? = null,
)

data class PlaylistItem(
    @field:SerializedName("snippet")
    val snippet: Snippet,
    @field:SerializedName("kind")
    val kind: String? = null,
    @field:SerializedName("etag")
    val etag: String? = null,
    @field:SerializedName("id")
    val id: String,
)

data class Medium(
    @field:SerializedName("width")
    val width: Int? = null,
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("height")
    val height: Int? = null,
)
