package com.neophron88.guidebook.di

import android.app.Application
import com.neophron88.database.di.DatabaseSourcesModule
import com.neophron88.network.di.NetworkServicesModule
import com.neophron88.upcoming.di.UpcomingFeatureDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkServicesModule::class,
        DatabaseSourcesModule::class,
    ]
)
interface GuideBookAppComponent : UpcomingFeatureDeps {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance application: Application): GuideBookAppComponent
    }

}