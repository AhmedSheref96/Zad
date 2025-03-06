package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CourseYoutubePlaylistViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<CourseYoutubePlaylistViewState> =
        MutableStateFlow(CourseYoutubePlaylistViewState(null))

    val stateFlow: StateFlow<CourseYoutubePlaylistViewState> = _stateFlow.asStateFlow()


}