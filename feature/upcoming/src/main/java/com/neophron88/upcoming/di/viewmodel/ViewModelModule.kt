package com.neophron88.upcoming.di.viewmodel

import androidx.lifecycle.ViewModel
import com.neophron88.feature.viewModelFactory.ViewModelKey
import com.neophron88.upcoming.domain.usecases.GetPagedUpcomingUseCase
import com.neophron88.upcoming.presentation.screens.list.UpcomingListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(UpcomingListViewModel::class)
    @Provides
    fun bindAllCategoriesViewModel(getPagedUpcomingUseCase: GetPagedUpcomingUseCase): ViewModel =
        UpcomingListViewModel(getPagedUpcomingUseCase)

}