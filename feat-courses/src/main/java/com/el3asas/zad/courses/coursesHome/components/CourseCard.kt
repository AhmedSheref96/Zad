package com.el3asas.zad.courses.coursesHome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(onClick = onClick) {
        Column {
            AsyncImage(model = "", contentDescription = "")
        }
    }
}

@Preview(name = "CourseCard")
@Composable
private fun PreviewCourseCard() {
    CourseCard()
}
