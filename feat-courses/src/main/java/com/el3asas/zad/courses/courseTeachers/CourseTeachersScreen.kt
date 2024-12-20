package com.el3asas.zad.courses.courseTeachers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.el3asas.zad.courses.courseTeachers.components.TeacherCard

@Composable
fun CourseTeachersScreen(
    state: CourseTeachersState,
    onAction: (CourseTeachersAction) -> Unit,
) {
    when (state) {
        is CourseTeachersState.LoadTeachers -> {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier,
            ) {
                itemsIndexed(state.teachers) { index, item ->
                    TeacherCard(
                        teacher = item,
                        onClick = { onAction(CourseTeachersAction.OnSelectTeacher) },
                    )
                }
            }
        }

        is CourseTeachersState.Error -> {}
        CourseTeachersState.Idle -> {}
        CourseTeachersState.Loading -> {}
    }
}

@Composable
@Preview(name = "CourseTeachers")
private fun CourseTeachersScreenPreview(
    @PreviewParameter(CourseTeachersStatePreviewParameterProvider::class)
    state: CourseTeachersState,
) {
    CourseTeachersScreen(
        state = state,
        onAction = {},
    )
}
