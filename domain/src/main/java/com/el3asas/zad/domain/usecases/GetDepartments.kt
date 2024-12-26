package com.el3asas.zad.domain.usecases

import com.el3asas.zad.domain.repos.DepartmentsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDepartments
    @Inject
    constructor(
        private val repo: DepartmentsRepo,
    ) {
        suspend operator fun invoke() =
            withContext(Dispatchers.IO) {
                repo.getDepartments()
            }
    }
