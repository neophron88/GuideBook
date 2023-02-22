package com.neophron88.upcoming.di.data

import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.upcoming.data.UpcomingRepositoryImpl
import com.neophron88.upcoming.domain.repository.UpcomingRepository
import com.neophron88.upcoming.di.UpcomingFeatureScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @UpcomingFeatureScope
    fun provideUpcomingRepository(
        local: UpcomingLocalDataSource,
        network: UpcomingNetworkDataSource,
    ): UpcomingRepository =
        UpcomingRepositoryImpl(local, network)


}