package com.el3asas.zad.data.reposImpl

import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.repos.CoursesRepo
import javax.inject.Inject

class CoursesRepoImpl
    @Inject
    constructor() : CoursesRepo {
        override suspend fun getCourses(): List<CourseModel> = emptyList()
    }
