package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

@Composable
fun CoursesHomeScreen(
    state: CoursesHomeState,
    onAction: (CoursesHomeAction) -> Unit,
) {

}

@Composable
@Preview(name = "CoursesHome")
private fun CoursesHomeScreenPreview(
    @PreviewParameter(CoursesHomeStatePreviewParameterProvider::class)
    state: CoursesHomeState,
) {
    CoursesHomeScreen(
        state = state,
        onAction = {},
    )
}
