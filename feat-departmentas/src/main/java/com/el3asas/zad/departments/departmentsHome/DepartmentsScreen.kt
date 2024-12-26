package com.el3asas.zad.departments.departmentsHome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.el3asas.zad.departments.departmentsHome.DepartmentsAction.OnDepartmentCardClicked
import com.el3asas.zad.departments.departmentsHome.components.CourseCard

@Composable
fun CoursesScreen(
    modifier: Modifier = Modifier,
    state: DepartmentsState,
    onAction: (DepartmentsAction) -> Unit,
) {
    when (state) {
        is DepartmentsState.LoadDepartments -> {
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
            ) {
                itemsIndexed(state.courses, key = { _, item -> item.id }) { _, item ->
                    CourseCard(
                        modifier = Modifier,
                        departmentModel = item,
                        onClick = { onAction(OnDepartmentCardClicked(item)) },
                    )
                }
            }
        }

        is DepartmentsState.Error -> {
            Text(modifier = modifier.fillMaxSize(), text = "Error")
        }

        DepartmentsState.Idle -> {
            Text(modifier = modifier.fillMaxSize(), text = "idle")
        }

        DepartmentsState.Loading -> {
            Text(modifier = modifier.fillMaxSize(), text = "Loading...")
        }

        is DepartmentsState.OpenDepartmentTeachers -> {
            Text(modifier = modifier.fillMaxSize(), text = "OpenCourseTeachers")
        }
    }
}
