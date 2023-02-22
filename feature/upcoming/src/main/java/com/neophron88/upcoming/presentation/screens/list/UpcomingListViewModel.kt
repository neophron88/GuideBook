package com.neophron88.upcoming.presentation.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.neophron88.mylibrary.ktx.require
import com.neophron88.upcoming.domain.usecases.GetPagedUpcomingUseCase
import kotlinx.coroutines.flow.flatMapLatest

class UpcomingListViewModel(
    private val getPagedUpcomingUseCase: GetPagedUpcomingUseCase
) : ViewModel() {

    private val refresh = MutableLiveData("")

    val upcomingPagingData = refresh.asFlow()
        .flatMapLatest { getPagedUpcomingUseCase() }
        .cachedIn(viewModelScope)

    fun refresh() =
        refresh.postValue(refresh.value.require())

}