package com.neophron88.upcoming.presentation

import androidx.fragment.app.Fragment
import com.neophron88.feature.contract.HasViewModelFactory
import com.neophron88.feature.helper.extractDependency
import com.neophron88.feature.viewModelFactory.ViewModelFactory
import com.neophron88.upcoming.R
import com.neophron88.upcoming.di.DaggerUpcomingFeatureComponent
import com.neophron88.upcoming.di.UpcomingFeatureComponent

class UpcomingHostFragment : Fragment(R.layout.upcoming_host), HasViewModelFactory {


    private val upcomingFeatureComponent: UpcomingFeatureComponent by lazy {
        DaggerUpcomingFeatureComponent.builder()
            .deps(extractDependency())
            .build()
    }

    override val viewModelFactory: ViewModelFactory by lazy {
        upcomingFeatureComponent.viewModelFactory
    }

}