package com.el3asas.zad.courses.coursesHome.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.systemdesign.theme.onPrimaryContainerLight
import com.el3asas.zad.systemdesign.theme.primaryContainerLight
import kotlin.random.Random


@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    courseModel: CourseModel,
    onClick: () -> Unit = {},
) {
    BoxWithConstraints {
        val maxSize = maxWidth
        Card(
            modifier = modifier.then(Modifier.size(maxSize)),
            onClick = onClick,
            colors = CardDefaults.cardColors().copy(
                containerColor = primaryContainerLight,
                contentColor = onPrimaryContainerLight
            )
        ) {
            if (courseModel.imageUrl.isEmpty())

                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    text = courseModel.title,
                    textAlign = TextAlign.Center
                )
            else AsyncImage(
                model = courseModel.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = courseModel.title
            )
        }
    }
}

@Preview(name = "CourseCard")
@Composable
private fun PreviewCourseCard() {
    CourseCard(
        courseModel = CourseModel(
            id = Random.nextInt(),
            title = "title",
            description = "description",
            imageUrl = "",
            teachers = emptyList()
        )
    )
}
