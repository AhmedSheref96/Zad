package com.el3sas.zad.components

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.TeacherModel
import com.el3asas.zad.systemdesign.R
import com.el3asas.zad.systemdesign.theme.AppTypography

@Composable
fun TeacherCard(
    modifier: Modifier = Modifier,
    teacher: TeacherModel,
    onClick: (TeacherModel) -> Unit,
) {
    Card(modifier = modifier.fillMaxWidth(), onClick = { onClick.invoke(teacher) }) {
        ConstraintLayout(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            val (img, name, arrow) = createRefs()
            AsyncImage(
                teacher.imageUrl,
                placeholder = painterResource(R.drawable.teacher_placeholder),
                contentDescription = teacher.name,
                contentScale= ContentScale.Crop,
                modifier =
                    Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .constrainAs(img) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },
            )
            Text(
                text = teacher.name,
                fontSize = AppTypography.bodyLarge.fontSize,
                textAlign = TextAlign.Start,
                modifier =
                    Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(img.end, margin = 10.dp)
                            end.linkTo(arrow.start)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        },
            )
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowRight,
                modifier =
                    Modifier
                        .height(46.dp)
                        .wrapContentSize(Alignment.Center)
                        .constrainAs(arrow) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTeacherCard() {
    TeacherCard(teacher = TeacherModel(name = "teacher")) {}
}
