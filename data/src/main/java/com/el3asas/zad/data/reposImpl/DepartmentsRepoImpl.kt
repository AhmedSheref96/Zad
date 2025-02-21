package com.el3asas.zad.data.reposImpl

import com.el3asas.zad.data.dataSources.DepartmentsDataSource
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.repos.DepartmentsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepartmentsRepoImpl
    @Inject
    constructor(
        private val departmentsDataSource: DepartmentsDataSource,
    ) : DepartmentsRepo {
        override suspend fun getDepartments(): Flow<List<DepartmentModel>> = departmentsDataSource.getDepartments()
    }
