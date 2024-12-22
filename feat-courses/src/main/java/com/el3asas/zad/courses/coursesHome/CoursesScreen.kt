package com.el3asas.zad.courses.coursesHome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
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
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
            ) {
                itemsIndexed(state.courses, key = { _, item -> item.id }) { _, item ->
                    CourseCard(
                        modifier = Modifier.padding(16.dp),
                        courseModel = item,
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
