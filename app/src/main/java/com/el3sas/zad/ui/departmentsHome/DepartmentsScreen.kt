package com.el3sas.zad.ui.departmentsHome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.systemdesign.R
import com.el3sas.zad.components.DepartmentCard
import com.el3sas.zad.ui.departmentsHome.DepartmentsAction.OnDepartmentCardClicked

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
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_padding_2)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_padding_2)),
                columns = GridCells.Adaptive(minSize = 120.dp),
                contentPadding = PaddingValues(dimensionResource(R.dimen.content_padding_2)),
            ) {
                itemsIndexed(state.courses, key = { _, item -> item.id }) { _, item ->
                    DepartmentCard(
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

@Preview(device = Devices.PIXEL_4)
@Composable
fun CoursesScreenPreview() {
    val courses =
        listOf(
            DepartmentModel(id = "1", title = "Course 1"),
            DepartmentModel(id = "2", title = "Course 2"),
            DepartmentModel(id = "3", title = "Course 3"),
            DepartmentModel(id = "4", title = "Course 4"),
        )
    CoursesScreen(
        state = DepartmentsState.LoadDepartments(courses),
        onAction = {},
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun CoursesScreenErrorPreview() {
    CoursesScreen(
        state = DepartmentsState.Error("Error!"),
        onAction = {},
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun CoursesScreenLoadingPreview() {
    CoursesScreen(
        state = DepartmentsState.Loading,
        onAction = {},
    )
}
