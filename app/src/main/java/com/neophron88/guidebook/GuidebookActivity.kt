package com.neophron88.guidebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neophron88.feature.contract.AppRouter

class GuidebookActivity : AppCompatActivity(), AppRouter {

    private val navController by navControllers(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setBackgroundDrawableResource(com.neophron88.theme.R.drawable.bg_day_night)
    }

    override fun routeFromSplashToUpcoming(args: Bundle?) =
        navController.navigate(R.id.action_splashFragment_to_upcomingHostFragment)

}