package com.neophron88.upcoming.presentation.screens.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.neophron88.mylibrary.rv_adapter_delegate.ItemDiffUtil
import com.neophron88.mylibrary.rv_adapter_delegate.ItemViewHolder
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.ItemUpcomingBinding
import com.neophron88.upcoming.domain.models.Upcoming

typealias onUpcomingItemClick = (url: String) -> Unit

class UpcomingAdapter(
    private val onUpcomingItemClick: onUpcomingItemClick,
    private val context: Context
) : PagingDataAdapter<Upcoming, UpcomingAdapter.Holder>(
    ItemDiffUtil(itemsTheSameValue = Upcoming::id)
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_upcoming, parent, false)
        return Holder(view, context, onUpcomingItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    class Holder(
        view: View,
        private val context: Context,
        private val onUpcomingItemClick: onUpcomingItemClick
    ) : ItemViewHolder<Upcoming>(view) {

        private val binding = ItemUpcomingBinding.bind(view)

        init {
            binding.cardView.setOnClickListener { onUpcomingItemClick(item.url) }
        }

        override fun onBind(item: Upcoming) = with(binding) {
            Glide.with(binding.root.context)
                .load(item.iconUrl)
                .into(binding.image)

            name.text = item.name
            endDate.text = context.getString(R.string.end_date_format, item.endDate)
        }
    }

}

