package com.el3asas.zad.domain.usecases

import com.el3asas.zad.domain.repos.CoursesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCourses
    @Inject
    constructor(
        private val repo: CoursesRepo,
    ) {
        suspend operator fun invoke() =
            withContext(Dispatchers.IO) {
                delay(2000)
                repo.getCourses()
            }
    }
