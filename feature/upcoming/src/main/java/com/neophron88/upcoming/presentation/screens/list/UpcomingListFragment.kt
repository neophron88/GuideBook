package com.neophron88.upcoming.presentation.screens.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.neophron88.feature.contract.HasViewModelFactory
import com.neophron88.ktx.fragment.scope.viewLifeCycleScope
import com.neophron88.mylibrary.ktx.fragment.findParentAs
import com.neophron88.mylibrary.ktx.fragment.getStringOrNull
import com.neophron88.mylibrary.ktx.fragment.viewLifeCycle
import com.neophron88.mylibrary.viewbinding_delegate.viewBindings
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.FragmentUpcomingListBinding
import com.neophron88.upcoming.presentation.screens.helper.getMessageErrorIfExists
import com.neophron88.upcoming.presentation.screens.list.adapter.UpcomingAdapter
import com.neophron88.upcoming.presentation.screens.list.adapter.UpcomingFooterAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UpcomingListFragment : Fragment(R.layout.fragment_upcoming_list) {

    private val binding: FragmentUpcomingListBinding by viewBindings()
    private val navController: NavController by viewLifeCycle { findNavController() }
    private val adapter: UpcomingAdapter by viewLifeCycle { setupAdapter() }

    private val viewModel: UpcomingListViewModel by viewModels(
        factoryProducer = { findParentAs<HasViewModelFactory>().viewModelFactory })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        setupSwipeToRefresh()
        observeUpcomingData()
        observeLoadState()

    }

    private fun setupAdapter() = UpcomingAdapter(
        context = requireContext(),
        onUpcomingItemClick = {}
    )

    private fun setupFooterAdapter() = UpcomingFooterAdapter(
        context = requireContext(),
        tryAgainAction = { adapter.retry() }
    )

    private fun setupList() = binding.apply {
        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter.withLoadStateFooter(setupFooterAdapter())
        (list.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun observeUpcomingData() = viewLifeCycleScope.launch {
        viewModel.upcomingPagingData.collectLatest {
            adapter.submitData(it)
        }
    }


    private fun observeLoadState() = viewLifeCycleScope.launch {
        adapter.loadStateFlow.collectLatest { state ->
            val refreshState = state.refresh
            binding.apply {
                swipeRefreshLayout.isRefreshing = refreshState is LoadState.Loading
                message.isVisible = refreshState is LoadState.Error
                message.text = getStringOrNull(refreshState.getMessageErrorIfExists())
            }
        }
    }
}

