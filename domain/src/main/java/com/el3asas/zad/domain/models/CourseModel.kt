package com.el3asas.zad.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CourseModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val courseYoutubeUrl: String,
    val teachers: List<TeacherModel>,
    val properties: List<PropertiesModel>,
) : Parcelable
