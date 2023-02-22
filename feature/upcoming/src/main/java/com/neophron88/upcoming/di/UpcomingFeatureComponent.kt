package com.neophron88.upcoming.di

import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.feature.viewModelFactory.ViewModelFactory
import com.neophron88.network.base.BaseUrl
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.upcoming.di.data.DataModule
import com.neophron88.upcoming.di.domain.DomainModule
import com.neophron88.upcoming.di.viewmodel.ViewModelModule
import dagger.Component


@UpcomingFeatureScope
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ],
    dependencies = [UpcomingFeatureDeps::class]
)
internal interface UpcomingFeatureComponent {

    val viewModelFactory: ViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: UpcomingFeatureDeps): Builder

        fun build(): UpcomingFeatureComponent
    }

}


interface UpcomingFeatureDeps {

    fun getUpcomingLocalDataSource(): UpcomingLocalDataSource

    fun getUpcomingNetworkDataSource(): UpcomingNetworkDataSource

    fun getBaseUrl(): BaseUrl
}