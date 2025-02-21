package com.el3sas.zad.ui.departmentTeachers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.el3asas.zad.domain.models.TeacherModel
import com.el3asas.zad.systemdesign.R
import com.el3sas.zad.components.TeacherCard

@Composable
fun DepartmentTeachersScreen(
    modifier: Modifier = Modifier,
    state: DepartmentTeachersState,
    onAction: (DepartmentTeachersAction) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        onAction(DepartmentTeachersAction.OnBackClicked)
                    },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "back button",
                    )
                }

                Text(
                    modifier = Modifier.padding(dimensionResource(R.dimen.content_padding_2)),
                    text = stringResource(R.string.select_teacher),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
    ) {
        when (state) {
            is DepartmentTeachersState.LoadTeachers -> {
                LazyColumn(
                    modifier =
                        Modifier
                            .padding(it)
                            .fillMaxSize(),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.content_padding_2)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.content_padding_1)),
                ) {
                    itemsIndexed(state.teachers) { index, item ->
                        TeacherCard(
                            teacher = item,
                            onClick = { onAction(DepartmentTeachersAction.OnSelectTeacher) },
                        )
                    }
                }
            }

            is DepartmentTeachersState.Error -> {}
            DepartmentTeachersState.Idle -> {}
            DepartmentTeachersState.Loading -> {}
        }
    }
}

@Preview
@Composable
fun DepartmentTeachersScreenPreview() {
    val teachers =
        listOf(
            TeacherModel(name = "teacher"),
            TeacherModel(name = "teacher"),
            TeacherModel(name = "teacher"),
        )
    val state = DepartmentTeachersState.LoadTeachers(teachers = teachers)
    DepartmentTeachersScreen(
        state = state,
        onAction = {},
    )
}
