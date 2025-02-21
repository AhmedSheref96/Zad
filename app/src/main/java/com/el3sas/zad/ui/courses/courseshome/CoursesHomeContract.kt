package com.el3sas.zad.ui.courses.courseshome

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * UI State that represents CoursesHomeScreen
 **/
sealed class CoursesHomeState {
    data object Loading : CoursesHomeState()

    data object Error : CoursesHomeState()

    data object Idle : CoursesHomeState()

    data object Success : CoursesHomeState()
}

/**
 * CoursesHome Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CoursesHomeAction {
    data object OnClick : CoursesHomeAction
}

/**
 * PreviewParameter Provider for CoursesHomeScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class CoursesHomeStatePreviewParameterProvider : PreviewParameterProvider<CoursesHomeState> {
    override val values: Sequence<CoursesHomeState>
        get() =
            sequenceOf()
}
