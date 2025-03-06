package com.el3asas.zad.domain.repos

import com.el3asas.zad.domain.models.DepartmentModel
import kotlinx.coroutines.flow.Flow

interface DepartmentsRepo {
    suspend fun getDepartments(): Flow<List<DepartmentModel>>
}
