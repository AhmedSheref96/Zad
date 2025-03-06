package com.el3asas.zad.departments.departmentsHome.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.systemdesign.theme.onPrimaryContainerLight
import com.el3asas.zad.systemdesign.theme.primaryContainerLight

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    departmentModel: DepartmentModel,
    onClick: () -> Unit = {},
) {
    BoxWithConstraints {
        val maxSize = maxWidth
        Card(
            modifier = modifier.then(Modifier.size(maxSize)),
            onClick = onClick,
            colors =
                CardDefaults.cardColors().copy(
                    containerColor = primaryContainerLight,
                    contentColor = onPrimaryContainerLight,
                ),
        ) {
            if (departmentModel.imageUrl.isEmpty()) {
                Text(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                    text = departmentModel.title,
                    textAlign = TextAlign.Center,
                )
            } else {
                AsyncImage(
                    model = departmentModel.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = departmentModel.title,
                )
            }
        }
    }
}
//
// @Preview(name = "CourseCard")
// @Composable
// private fun PreviewCourseCard() {
//    CourseCard(
//        departmentModel = DepartmentModel(),
//    )
// }
