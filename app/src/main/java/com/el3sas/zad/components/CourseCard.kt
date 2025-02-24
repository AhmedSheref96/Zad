package com.el3sas.zad.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.el3asas.zad.domain.models.CourseModel

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: CourseModel,
    onCourseClick: () -> Unit = {},
) {
    Card {
        ConstraintLayout {
        }
    }
}
