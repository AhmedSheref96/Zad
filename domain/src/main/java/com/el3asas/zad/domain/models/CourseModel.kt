package com.el3asas.zad.domain.models

data class CourseModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val teachers: List<TeacherModel>,
    val department: List<DepartmentModel>,
    val properties: List<PropertiesModel>,
)

data class PropertiesModel(
    val iconUrl: String,
    val title: String,
    val value: String,
)
