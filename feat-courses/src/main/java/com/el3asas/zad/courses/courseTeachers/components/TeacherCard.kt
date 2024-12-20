package com.el3asas.zad.courses.courseTeachers.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.TeacherModel
import com.el3asas.zad.systemdesign.theme.AppTypography

@Composable
fun TeacherCard(
    modifier: Modifier = Modifier,
    teacher: TeacherModel,
    onClick: (TeacherModel) -> Unit,
) {
    Card(modifier = modifier, onClick = { onClick.invoke(teacher) }) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                teacher.imageUrl,
                contentDescription = teacher.name,
                modifier =
                    Modifier
                        .size(46.dp)
                        .clip(CircleShape),
            )
            Text(
                text = teacher.name,
                fontSize = AppTypography.bodyLarge.fontSize,
                modifier =
                    Modifier
                        .height(46.dp)
                        .wrapContentSize(Alignment.Center),
            )
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowRight,
                modifier =
                    Modifier
                        .height(46.dp)
                        .wrapContentSize(Alignment.Center),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTeacherCard() {
    TeacherCard(teacher = TeacherModel("1", "Ahmed", "")) {}
}
