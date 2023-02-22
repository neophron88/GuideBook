package com.neophron88.upcoming.presentation.screens.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.neophron88.feature.contract.HasViewModelFactory
import com.neophron88.mylibrary.ktx.fragment.findParentAs
import com.neophron88.mylibrary.ktx.fragment.interceptOnBackPressed
import com.neophron88.mylibrary.ktx.fragment.viewLifeCycle
import com.neophron88.mylibrary.ktx.require
import com.neophron88.mylibrary.viewbinding_delegate.viewBindings
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.FragmentUpcomingDetailBinding
import com.neophron88.upcoming.presentation.contract.ActionBarBehavior
import com.neophron88.upcoming.presentation.contract.ActionBarBehaviorHandler
import com.neophron88.upcoming.presentation.helper.createWebViewClient
import com.neophron88.upcoming.presentation.helper.showToast

class UpcomingDetailFragment : Fragment(R.layout.fragment_upcoming_detail) {

    private val binding: FragmentUpcomingDetailBinding by viewBindings()

    private val navController: NavController by viewLifeCycle { findNavController() }

    private val guideName by viewLifeCycle {
        requireArguments().getString(GUIDE_NAME).require()
    }
    private val guideId by viewLifeCycle {
        requireArguments().getLong(GUIDE_ID).require()
    }

    private val viewModel: UpcomingDetailViewModel by viewModels(
        factoryProducer = { findParentAs<HasViewModelFactory>().viewModelFactory }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchGuide()
        observeUiState()
        observeUiEvent()
    }

    private fun fetchGuide() = viewModel.requireUpcoming(guideId)


    private fun observeUiState() = viewModel.uiState.observe(viewLifecycleOwner) {
        val url = it.data?.url ?: return@observe
        setupWebView(url)
        setupSwipeRefreshLayout()
    }

    private fun setupWebView(url: String) = binding.apply {
        webView.webViewClient = createWebViewClient(
            onPageStarted = { swipeRefreshLayout.isRefreshing = true },
            onPageFinished = { swipeRefreshLayout.isRefreshing = false }
        )
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        interceptBackPress()
    }

    private fun interceptBackPress() = interceptOnBackPressed(viewLifecycleOwner) {
        val webView = binding.webView
        if (webView.canGoBack()) webView.goBack()
        else navController.popBackStack()

    }

    private fun setupSwipeRefreshLayout() = binding.apply {
        swipeRefreshLayout.setOnRefreshListener {
            webView.reload()
        }
    }

    private fun observeUiEvent() = viewModel.uiEvent.observe(viewLifecycleOwner) {
        if (it is UpcomingDetailUiEvent.Message)
            showToast(it.resId)
    }


    override fun onStart() {
        super.onStart()
        findParentAs<ActionBarBehaviorHandler>()
            .setActionBarBehavior(object : ActionBarBehavior(viewLifecycleOwner) {
                override val title: String = guideName
                override val shouldApplyNavigateUp: Boolean = true
            })
    }

    companion object {
        const val GUIDE_NAME = "GUIDE_NAME"
        const val GUIDE_ID = "GUIDE_ID"
    }
}