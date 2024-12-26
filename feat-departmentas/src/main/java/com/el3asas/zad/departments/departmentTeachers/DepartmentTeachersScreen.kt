package com.el3asas.zad.departments.departmentTeachers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.el3asas.zad.departments.departmentTeachers.components.TeacherCard

@Composable
fun DepartmentTeachersScreen(
    modifier: Modifier = Modifier,
    state: DepartmentTeachersState,
    onAction: (DepartmentTeachersAction) -> Unit,
) {
    when (state) {
        is DepartmentTeachersState.LoadTeachers -> {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.Top,
            ) {
                itemsIndexed(state.teachers) { index, item ->
                    TeacherCard(
                        teacher = item,
                        onClick = { onAction(DepartmentTeachersAction.OnSelectTeacher) },
                    )
                }
            }
        }

        is DepartmentTeachersState.Error -> {}
        DepartmentTeachersState.Idle -> {}
        DepartmentTeachersState.Loading -> {}
    }
}
