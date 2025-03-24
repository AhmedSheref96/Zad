package com.el3asas.zad.systemdesign.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.TeacherModel
import com.el3asas.zad.systemdesign.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SmallTeachersList(
    modifier: Modifier = Modifier,
    list: List<TeacherModel>,
    horizontalArrangement: Arrangement.Horizontal =
        Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Start,
        ),
    verticalArrangement: Arrangement.Vertical =
        Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Top,
        ),
    contentVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentHorizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
) {
    if (list.isNotEmpty()) {
        FlowRow(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalArrangement = verticalArrangement,
        ) {
            list.forEachIndexed { index, teacher ->
                if (index == 0) {
                    SmallTeacherItem(
                        modifier = Modifier,
                        teacher = teacher,
                        contentVerticalAlignment = contentVerticalAlignment,
                        contentHorizontalAlignment = contentHorizontalAlignment,
                    )
                } else {
                    val overlapOffset = (-15).dp
                    SmallTeacherItem(
                        modifier =
                            Modifier
                                .offset(x = overlapOffset * index)
                                .zIndex(index.toFloat()),
                        teacher = teacher,
                        contentVerticalAlignment = contentVerticalAlignment,
                        contentHorizontalAlignment = contentHorizontalAlignment,
                    )
                }
            }
        }
    }
}

@Composable
fun SmallTeacherItem(
    modifier: Modifier = Modifier,
    teacher: TeacherModel,
    contentVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentHorizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
) {
    Row(
        modifier = modifier,
        verticalAlignment = contentVerticalAlignment,
        horizontalArrangement = contentHorizontalAlignment,
    ) {
        AsyncImage(
            teacher.imageUrl,
            placeholder = painterResource(R.drawable.teacher_placeholder),
            contentDescription = teacher.name,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .border(1.dp, Color.Black, CircleShape)
                    .size(20.dp)
                    .clip(CircleShape),
        )
    }
}

@Preview
@Composable
fun SmallTeacherItemPreview() {
    val teacher =
        TeacherModel(
            id = "1",
            name = "Teacher Name",
            imageUrl = "https://example.com/image.jpg",
        )
    SmallTeacherItem(teacher = teacher)
}
