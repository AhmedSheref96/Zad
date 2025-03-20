package com.el3sas.zad.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.models.PropertiesModel
import com.el3asas.zad.domain.models.TeacherModel
import com.google.firebase.util.nextAlphanumericString
import kotlin.random.Random

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: CourseModel,
    onCourseClick: (CourseModel) -> Unit = {},
) {
    Card(
        modifier = modifier,
        onClick = {
            onCourseClick(course)
        },
    ) {
        Column {
            if (course.imageUrl.isNotEmpty()) {
                Box(
                    modifier =
                        Modifier.aspectRatio(19f / 16f),
                ) {
                    AsyncImage(
                        model = course.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
            Column(
                modifier =
                    Modifier
                        .padding(8.dp),
            ) {
                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = course.title,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = course.description,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(Modifier.height(4.dp))

                SmallTeachersList(
                    modifier =
                        Modifier.fillMaxWidth(),
                    list = course.teachers,
                )

                Spacer(Modifier.height(4.dp))

                PropertiesList(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    properties = course.properties,
                )
            }
        }
    }
}

@Preview(device = PIXEL_TABLET)
@Composable
fun CourseCardPreview() {
    val teacher =
        TeacherModel(id = "1", name = "Teacher 1", imageUrl = "https://via.placeholder.com/150")
    val department =
        DepartmentModel(
            id = "1",
            title = "Department 1",
            description = "Description 1",
            imageUrl = "https://via.placeholder.com/150",
        )
    val properties =
        listOf(
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 1",
                value = "Value 1",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 2",
                value = "Value 2",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 1",
                value = "Value 1",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 2",
                value = "Value 2",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 1",
                value = "Value 1",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 2",
                value = "Value 2",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 1",
                value = "Value 1",
            ),
            PropertiesModel(
                iconUrl = "https://via.placeholder.com/150",
                title = "Property 2",
                value = "Value 2",
            ),
        )
    val course =
        CourseModel(
            title = "Course Title",
            description = "Course Description",
            imageUrl = "https://via.placeholder.com/150",
            teachers = listOf(teacher),
            properties = properties,
            id = Random.nextAlphanumericString(5),
            courseYoutubeUrl = "",
        )
    CourseCard(course = course)
}
