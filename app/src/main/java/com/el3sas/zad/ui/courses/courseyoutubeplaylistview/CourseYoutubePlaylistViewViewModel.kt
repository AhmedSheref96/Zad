package com.el3sas.zad.ui.courses.courseyoutubeplaylistview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.usecases.GetYoutubePlaylistData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CourseYoutubePlaylistViewViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val getYoutubePlaylistData: GetYoutubePlaylistData,
    ) : ViewModel() {
        private val _stateFlow: MutableStateFlow<CourseYoutubePlaylistViewState> =
            MutableStateFlow(
                CourseYoutubePlaylistViewState(
                    courseModel = savedStateHandle.get<CourseModel>("courseModel")!!,
                ),
            )

        val stateFlow: StateFlow<CourseYoutubePlaylistViewState> = _stateFlow.asStateFlow()

        val playlistDataPager =
            getYoutubePlaylistData(stateFlow.value.courseModel.courseYoutubeUrl).flow.cachedIn(
                viewModelScope,
            )

        fun updateState(state: CourseYoutubePlaylistViewState) {
            _stateFlow.update { state }
        }
    }
