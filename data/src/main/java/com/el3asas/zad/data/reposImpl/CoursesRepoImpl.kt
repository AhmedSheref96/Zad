package com.el3asas.zad.data.reposImpl

import com.el3asas.zad.data.dataSources.CoursesDataSource
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.repos.CoursesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoursesRepoImpl
    @Inject
    constructor(
        private val coursesDataSource: CoursesDataSource,
    ) : CoursesRepo {
        override fun getCourses(teacherId: String): Flow<List<CourseModel>> = coursesDataSource.getCourses(teacherId)
    }
