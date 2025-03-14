package com.el3asas.zad.departments.departmentsHome

import com.el3asas.zad.domain.models.DepartmentModel
import kotlinx.serialization.Serializable

sealed interface DepartmentRoutes {
    @Serializable
    data object DepartmentsList : DepartmentRoutes

    @Serializable
    data class DepartmentTeachers(
        val departmentModel: DepartmentModel,
    ) : DepartmentRoutes
}

sealed class DepartmentsState {
    data object Idle : DepartmentsState()

    data object Loading : DepartmentsState()

    data class LoadDepartments(
        val courses: List<DepartmentModel> = emptyList(),
    ) : DepartmentsState()

    data class OpenDepartmentTeachers(
        val departmentModel: DepartmentModel,
    ) : DepartmentsState()

    data class Error(
        val message: String,
    ) : DepartmentsState()
}

sealed interface DepartmentsAction {
    data class OnDepartmentCardClicked(
        val departmentModel: DepartmentModel,
    ) : DepartmentsAction
}
