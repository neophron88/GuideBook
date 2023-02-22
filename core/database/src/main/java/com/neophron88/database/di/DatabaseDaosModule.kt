package com.neophron88.database.di

import com.neophron88.database.GuideBookDatabase
import com.neophron88.database.upcoming.roomImpl.UpcomingDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DatabaseDaosModule {

    @Provides
    @Singleton
    fun provideAllGenresDao(database: GuideBookDatabase): UpcomingDao =
        database.getUpcomingDao()
}