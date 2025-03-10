package com.el3asas.zad.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PropertiesModel(
    val iconUrl: String,
    val title: String,
    val value: String,
) : Parcelable
