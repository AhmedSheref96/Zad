package com.el3asas.zad.domain.usecases

import com.el3asas.zad.domain.repos.CoursesRepo
import javax.inject.Inject

class GetCourses
    @Inject
    constructor(
        private val repo: CoursesRepo,
    ) {
        operator fun invoke(teacherId: String) = repo.getCourses(teacherId)
    }
