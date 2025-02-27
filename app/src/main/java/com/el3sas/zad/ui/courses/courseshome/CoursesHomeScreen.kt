package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.el3asas.zad.systemdesign.R
import com.el3sas.zad.components.CourseCard

@Composable
fun CoursesHomeScreen(
    modifier: Modifier = Modifier,
    state: CoursesHomeState,
    onAction: (CoursesHomeAction) -> Unit,
) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                CoursesHomeState.Idle -> {
                }

                CoursesHomeState.Loading -> {
                    Text(text = "Loading...")
                }

                CoursesHomeState.Error -> {
                    Text(text = "Error...")
                }

                is CoursesHomeState.CoursesLoaded -> {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_padding_2)),
                    ) {
                        items(items = state.courses, key = { it.id }) {
                            CourseCard(
                                course = it,
                                onCourseClick = { onAction(CoursesHomeAction.OnCourseClicked(it)) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "CoursesHome")
private fun CoursesHomeScreenPreview(
    @PreviewParameter(CoursesHomeStatePreviewParameterProvider::class) state: CoursesHomeState,
) {
    CoursesHomeScreen(
        state = state,
        onAction = {},
    )
}
