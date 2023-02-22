package com.neophron88.database.di

import android.app.Application
import com.neophron88.database.GuideBookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): GuideBookDatabase =
        GuideBookDatabase.getDatabase(application)

}