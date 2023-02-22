package com.neophron88.upcoming.di.domain

import com.neophron88.upcoming.domain.repository.UpcomingRepository
import com.neophron88.upcoming.domain.usecases.GetPagedUpcomingUseCase
import com.neophron88.upcoming.domain.usecases.GetUpcomingUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetPagedUpcomingUseCase(repository: UpcomingRepository): GetPagedUpcomingUseCase =
        GetPagedUpcomingUseCase(repository)


    @Provides
    fun provideGetUpcomingUseCase(repository: UpcomingRepository): GetUpcomingUseCase =
        GetUpcomingUseCase(repository)

}