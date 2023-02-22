package com.neophron88.database.di

import com.neophron88.database.GuideBookDatabase
import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.database.upcoming.roomImpl.UpcomingRoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DatabaseSourcesModule {


    @Provides
    @Singleton
    fun provideUpcomingLocalDataSource(database: GuideBookDatabase): UpcomingLocalDataSource =
        UpcomingRoomDataSource(database.getUpcomingDao())
}