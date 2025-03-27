package com.el3sas.zad.ui

import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.models.DepartmentModel
import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object DepartmentsList : Destinations

    @Serializable
    data class DepartmentTeachers(
        val departmentModel: DepartmentModel,
    ) : Destinations

    @Serializable
    data class Courses(
        val teacherId: String,
    ) : Destinations

    @Serializable
    data class YoutubePlaylist(
        val courseModel: CourseModel,
    ) : Destinations
}
