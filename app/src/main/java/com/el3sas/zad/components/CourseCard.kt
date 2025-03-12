package com.el3sas.zad.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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
        modifier =
            Modifier
                .fillMaxWidth(),
        onClick = {
            onCourseClick(course)
        },
    ) {
        Row(
            modifier =
                Modifier
                    .height(intrinsicSize = IntrinsicSize.Min),
            horizontalArrangement = Arrangement.End,
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(2f)
                        .padding(8.dp),
            ) {
                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = course.title,
                    fontSize = 20.sp,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = course.description,
                )

                Spacer(Modifier.height(12.dp))

                SmallTeachersList(
                    modifier =
                        Modifier.fillMaxWidth(),
                    list = course.teachers,
                )

                Spacer(Modifier.height(8.dp))

                PropertiesList(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    properties = course.properties,
                )
            }
            Box(
                modifier =
                    Modifier
                        .weight(1f),
            ) {
                AsyncImage(
                    model = course.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .circleGradientBackground(),
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
