package com.neophron88.guidebook

import android.app.Application
import com.neophron88.feature.contract.DependencyProvider
import com.neophron88.guidebook.di.DaggerGuideBookAppComponent

class GuidebookApp : Application(), DependencyProvider {


    override val dependency: Any by lazy {
        DaggerGuideBookAppComponent
            .factory()
            .create(this)
    }
}