package com.el3asas.zad.domain.repos

import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.models.TeacherModel
import kotlinx.coroutines.flow.Flow

interface TeachersRepo {

    suspend fun getTeachersForDepartment(departmentModel: DepartmentModel): Flow<List<TeacherModel>>
}
