package com.neophron88.upcoming.di.data

import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.network.base.BaseUrl
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.upcoming.data.UpcomingRepositoryImpl
import com.neophron88.upcoming.di.UpcomingFeatureScope
import com.neophron88.upcoming.domain.repository.UpcomingRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @UpcomingFeatureScope
    fun provideUpcomingRepository(
        baseUrl: BaseUrl,
        local: UpcomingLocalDataSource,
        network: UpcomingNetworkDataSource,
    ): UpcomingRepository =
        UpcomingRepositoryImpl(baseUrl, local, network)


}