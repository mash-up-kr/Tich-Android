package com.example.tichandroid.view.ui.showcycle

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ShowCycleAdapter : ListAdapter<ShowCycleAdapterItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private val DIFF_CALLBACK =
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<ShowCycleAdapterItem>() {
                override fun areItemsTheSame(
                    oldItem: ShowCycleAdapterItem,
                    newItem: ShowCycleAdapterItem
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ShowCycleAdapterItem,
                    newItem: ShowCycleAdapterItem
                ): Boolean = oldItem == newItem
            }).build()
    }
}