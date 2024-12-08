package com.el3asas.zad.domain.repos

import com.el3asas.zad.domain.models.CourseModel

interface CoursesRepo {
    suspend fun getCourses(): List<CourseModel>
}
