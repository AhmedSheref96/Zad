package com.el3asas.zad.data.reposImpl

import com.el3asas.zad.data.dataSources.TeachersDataSource
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.models.TeacherModel
import com.el3asas.zad.domain.repos.TeachersRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeachersRepoImpl
    @Inject
    constructor(
        private val teachersDataSource: TeachersDataSource,
    ) : TeachersRepo {
        override suspend fun getTeachersForDepartment(departmentModel: DepartmentModel): Flow<List<TeacherModel>> =
            teachersDataSource.getTeachersForDepartment(departmentModel)
    }
