package com.neophron88.feature.contract

import android.os.Bundle
import android.view.View

interface AppRouter {

    fun routeFromSplashToUpcoming(
        args: Bundle? = null,
        vararg sharedElements: Pair<View, String>
    )
}