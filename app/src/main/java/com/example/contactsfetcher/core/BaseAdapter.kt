package com.example.contactsfetcher.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, I : Any>(
    protected val items: MutableList<I>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<I>) {
        val diffUtil = DiffUtil.calculateDiff(getDiffUtilsCallback(items, newItems))
        diffUtil.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newItems)
    }

    abstract fun getDiffUtilsCallback(oldItems: List<I>, newItems: List<I>): DiffUtil.Callback
}