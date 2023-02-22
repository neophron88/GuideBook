package com.neophron88.upcoming.presentation.screens.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.ItemUpcomingFooterBinding
import com.neophron88.upcoming.presentation.helper.getStringOrNull
import com.neophron88.upcoming.presentation.helper.getMessageErrorIfExists

typealias TryAgainAction = () -> Unit

class UpcomingFooterAdapter(
    private val context: Context,
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<UpcomingFooterAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_upcoming_footer, parent, false)
        return Holder(view, context, tryAgainAction)
    }

    class Holder(
        view: View,
        private val context: Context,
        private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemUpcomingFooterBinding.bind(view)

        init {
            binding.tryAgain.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            message.isVisible = loadState is LoadState.Error
            tryAgain.isVisible = loadState is LoadState.Error
            progressBar.isVisible = loadState is LoadState.Loading
            message.text = context.getStringOrNull(loadState.getMessageErrorIfExists())

        }
    }

}