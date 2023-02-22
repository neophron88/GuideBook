package com.neophron88.upcoming.di.viewmodel

import androidx.lifecycle.ViewModel
import com.neophron88.feature.viewModelFactory.ViewModelKey
import com.neophron88.upcoming.domain.usecases.GetPagedUpcomingUseCase
import com.neophron88.upcoming.domain.usecases.GetUpcomingUseCase
import com.neophron88.upcoming.presentation.screens.detail.UpcomingDetailViewModel
import com.neophron88.upcoming.presentation.screens.list.UpcomingListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(UpcomingListViewModel::class)
    @Provides
    fun bindUpcomingListViewModel(getPagedUpcomingUseCase: GetPagedUpcomingUseCase): ViewModel =
        UpcomingListViewModel(getPagedUpcomingUseCase)

    @IntoMap
    @ViewModelKey(UpcomingDetailViewModel::class)
    @Provides
    fun bindUpcomingDetailViewModel(getUpcomingUseCase: GetUpcomingUseCase): ViewModel =
        UpcomingDetailViewModel(getUpcomingUseCase)

}