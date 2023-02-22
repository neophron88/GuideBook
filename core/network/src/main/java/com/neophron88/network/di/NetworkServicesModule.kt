package com.neophron88.network.di

import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.network.upcoming.retrofitImpl.UpcomingRetrofitDataSource
import com.neophron88.network.upcoming.retrofitImpl.UpcomingService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class NetworkServicesModule {

    @Provides
    @Singleton
    fun provideUpcomingDataSource(retrofit: Retrofit): UpcomingNetworkDataSource {
        val service = retrofit.create(UpcomingService::class.java)
        return UpcomingRetrofitDataSource(service)
    }
}