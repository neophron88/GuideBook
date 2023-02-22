package com.neophron88.mylibrary.rv_adapter_delegate

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<I : Any>(
    view: View
) : RecyclerView.ViewHolder(view) {

    lateinit var item: I

    fun bind(item: I) {
        this.item = item
        onBind(item)
    }

    fun bind(item: I, payloads: MutableList<Any>) {
        this.item = item
        onBind(item, payloads)
    }


    abstract fun onBind(item: I)

    open fun onBind(item: I, payloads: MutableList<Any>) {}

    open fun unBind() {}

}