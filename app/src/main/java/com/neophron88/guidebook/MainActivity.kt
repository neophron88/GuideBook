package com.neophron88.guidebook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.neophron88.feature.contract.AppRouter

class MainActivity : AppCompatActivity(), AppRouter {

    private val navController by navControllers(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun routeFromSplashToUpcoming(
        args: Bundle?,
        vararg sharedElements: Pair<View, String>
    ) {
        navController.navigate(
            R.id.action_splashFragment_to_upcomingHostFragment, null, null,
            FragmentNavigatorExtras(*sharedElements)
        )
    }
}