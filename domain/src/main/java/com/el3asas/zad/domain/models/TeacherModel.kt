package com.el3asas.zad.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TeacherModel(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
)
