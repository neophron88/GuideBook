package com.neophron88.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.neophron88.feature.contract.AppRouter
import com.neophron88.mylibrary.ktx.postDelayed
import com.neophron88.mylibrary.ktx.takeAs

class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() = viewLifecycleOwner.postDelayed(1000) {
        requireActivity().takeAs<AppRouter>().routeFromSplashToUpcoming()
    }
}