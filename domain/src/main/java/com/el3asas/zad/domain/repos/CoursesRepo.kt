package com.el3asas.zad.domain.repos

import com.el3asas.zad.domain.models.CourseModel
import kotlinx.coroutines.flow.Flow

interface CoursesRepo {
    fun getCourses(teacherId: String): Flow<List<CourseModel>>
}
