package com.el3asas.zad.domain.models

data class CourseModel(
    val title: String,
    val description: String,
    val teachers: List<TeacherModel>,
    val department: List<DepartmentModel>,
    val videosNumber: Int = 1,
    val documentsNumber: Int = 0,
    val assignmentsNumber: Int = 0,
    val durationInMinutes: Int = 0,
)
