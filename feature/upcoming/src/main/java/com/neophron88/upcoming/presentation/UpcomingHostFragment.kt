package com.neophron88.upcoming.presentation

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.neophron88.feature.contract.HasViewModelFactory
import com.neophron88.feature.helper.extractDependency
import com.neophron88.feature.viewModelFactory.ViewModelFactory
import com.neophron88.mylibrary.ktx.fragment.viewLifeCycle
import com.neophron88.mylibrary.ktx.onStop
import com.neophron88.mylibrary.viewbinding_delegate.viewBindings
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.UpcomingHostBinding
import com.neophron88.upcoming.di.DaggerUpcomingFeatureComponent
import com.neophron88.upcoming.di.UpcomingFeatureComponent
import com.neophron88.upcoming.presentation.contract.ActionBarBehavior
import com.neophron88.upcoming.presentation.contract.ActionBarBehaviorHandler
import com.neophron88.upcoming.presentation.helper.findNavController

class UpcomingHostFragment :
    Fragment(R.layout.upcoming_host),
    HasViewModelFactory,
    ActionBarBehaviorHandler {

    private val binding: UpcomingHostBinding by viewBindings()
    private val childNavController: NavController by viewLifeCycle {
        childFragmentManager.findNavController()
    }
    private val upcomingFeatureComponent: UpcomingFeatureComponent by lazy {
        DaggerUpcomingFeatureComponent.builder()
            .deps(extractDependency())
            .build()
    }

    override val viewModelFactory: ViewModelFactory by lazy {
        upcomingFeatureComponent.viewModelFactory
    }

    override fun setActionBarBehavior(behavior: ActionBarBehavior) {
        val lifecycleOwner = behavior.lifecycleOwner
        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) return

        binding.apply {

            toolbar.title = behavior.title

            if (behavior.shouldApplyNavigateUp) {
                toolbar.setNavigationOnClickListener { childNavController.popBackStack() }
                toolbar.navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    com.neophron88.theme.R.drawable.ic_back
                )
            }

            behavior.applyMenu?.let {
                toolbar.inflateMenu(it.menu)
                toolbar.setOnMenuItemClickListener { menuItem -> it.onMenuItemClick(menuItem) }
            }

            lifecycleOwner.onStop {
                toolbar.title = null
                toolbar.menu.clear()
                toolbar.setNavigationOnClickListener(null)
                toolbar.navigationIcon = null
                toolbar.setOnMenuItemClickListener(null)
            }
        }
    }


}