package com.el3asas.zad.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CourseModel(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val teachers: List<TeacherModel>,
)
