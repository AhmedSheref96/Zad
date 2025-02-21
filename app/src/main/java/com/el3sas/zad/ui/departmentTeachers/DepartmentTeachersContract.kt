package com.el3sas.zad.ui.departmentTeachers

import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.models.TeacherModel

sealed class DepartmentTeachersState {
    data object Idle : DepartmentTeachersState()

    data object Loading : DepartmentTeachersState()

    data class Error(
        val message: String,
    ) : DepartmentTeachersState()

    data class LoadTeachers(
        val teachers: List<TeacherModel>,
    ) : DepartmentTeachersState()
}

sealed interface DepartmentTeachersAction {
    data object OnSelectTeacher : DepartmentTeachersAction
    data object OnBackClicked : DepartmentTeachersAction
    data class LoadTeachersForDepartment(
        val model: DepartmentModel,
    ) : DepartmentTeachersAction
}
