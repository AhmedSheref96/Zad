package com.el3asas.zad.courses.coursesHome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.el3asas.zad.courses.coursesHome.CoursesAction.OnCourseCardClicked
import com.el3asas.zad.courses.coursesHome.components.CourseCard

@Composable
fun CoursesScreen(
    modifier: Modifier = Modifier,
    state: CoursesState,
    onAction: (CoursesAction) -> Unit,
) {
    when (state) {
        is CoursesState.LoadCourses -> {
            LazyColumn {
                itemsIndexed(state.courses) { _, item ->
                    CourseCard(
                        modifier = modifier,
                        onClick = { onAction(OnCourseCardClicked(item)) },
                    )
                }
            }
        }

        is CoursesState.AddCourseToFavorite -> {
            Text(modifier = modifier.fillMaxSize(), text = "AddCourseToFavorite")
        }

        is CoursesState.Error -> {
            Text(modifier = modifier.fillMaxSize(), text = "Error")
        }

        CoursesState.Idle -> {
            Text(modifier = modifier.fillMaxSize(), text = "idle")
        }

        CoursesState.Loading -> {
            Text(modifier = modifier.fillMaxSize(), text = "Loading...")
        }

        is CoursesState.OpenCourseTeachers -> {
            Text(modifier = modifier.fillMaxSize(), text = "OpenCourseTeachers")
        }
    }
}

@Composable
@Preview(name = "Courses")
private fun CoursesScreenPreview(
    @PreviewParameter(CoursesStatePreviewParameterProvider::class)
    state: CoursesState,
) {
    CoursesScreen(
        state = state,
        onAction = {},
    )
}
