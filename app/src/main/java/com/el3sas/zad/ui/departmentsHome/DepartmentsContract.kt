package com.el3sas.zad.ui.departmentsHome

import com.el3asas.zad.domain.models.DepartmentModel
import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object DepartmentsList : Destinations

    @Serializable
    data class DepartmentTeachers(
        val departmentModel: DepartmentModel,
    ) : Destinations

    @Serializable
    data class Courses(
        val teacherId: String,
    ) : Destinations
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
