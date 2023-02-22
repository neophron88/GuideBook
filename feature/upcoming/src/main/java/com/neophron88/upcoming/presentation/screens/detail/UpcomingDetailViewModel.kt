package com.neophron88.upcoming.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neophron88.feature.result.SingleResult
import com.neophron88.mylibrary.single_use_data.MutableSingleUseData
import com.neophron88.mylibrary.single_use_data.SingleUseData
import com.neophron88.upcoming.R
import com.neophron88.upcoming.domain.usecases.GetUpcomingUseCase
import kotlinx.coroutines.launch

class UpcomingDetailViewModel(
    private val getUpcomingUseCase: GetUpcomingUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UpcomingDetailUiState>()
    val uiState: LiveData<UpcomingDetailUiState> get() = _uiState

    private val _uiEvent = MutableSingleUseData<UpcomingDetailUiEvent>()
    val uiEvent: SingleUseData<UpcomingDetailUiEvent> get() = _uiEvent


    fun requireUpcoming(upcomingId: Long) = viewModelScope.launch {
        val result = getUpcomingUseCase(upcomingId)
        if (result is SingleResult.Success)
            _uiState.value = UpcomingDetailUiState(data = result.value)
        else if (result is SingleResult.Error)
            _uiEvent.value = UpcomingDetailUiEvent.Message(R.string.something_went_wrong)

    }
}
