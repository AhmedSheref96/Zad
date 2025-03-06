package com.el3asas.zad.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TeacherModel(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
) : Parcelable
