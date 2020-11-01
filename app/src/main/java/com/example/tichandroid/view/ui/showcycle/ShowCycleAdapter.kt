package com.example.tichandroid.view.ui.showcycle

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tichandroid.R
import com.mashup.android.base.extension.inflate
import kotlinx.android.synthetic.main.item_show_cycle_banner.view.*
import kotlinx.android.synthetic.main.item_show_cycle_header.view.*
import kotlinx.android.synthetic.main.item_show_cycle_item.view.*
import kotlinx.android.synthetic.main.item_show_cycle_item_header.view.*

class ShowCycleAdapter(
    private val listener: OnClickListener?
) : ListAdapter<ShowCycleAdapterItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ShowCycleAdapterItem.Header -> VIEW_TYPE_HEADER
        is ShowCycleAdapterItem.Banner -> VIEW_TYPE_BANNER
        is ShowCycleAdapterItem.ItemHeader -> VIEW_TYPE_ITEM_HEADER
        else -> VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.inflate(viewType)
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(itemView)
            VIEW_TYPE_BANNER -> BannerViewHolder(itemView)
            VIEW_TYPE_ITEM_HEADER -> ItemHeaderViewHolder(itemView)
            else -> ItemViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position !in 0 until itemCount) return
        val item = getItem(position) ?: return

        when {
            holder is HeaderViewHolder && item is ShowCycleAdapterItem.Header -> holder.bind(item)
            holder is BannerViewHolder && item is ShowCycleAdapterItem.Banner -> holder.bind(item)
            holder is ItemHeaderViewHolder &&
                    item is ShowCycleAdapterItem.ItemHeader -> holder.bind(item)
            holder is ItemViewHolder && item is ShowCycleAdapterItem.Item -> holder.bind(item)
        }
    }

    interface OnClickListener {
        fun onHeaderSettingClick()
        fun onBannerNextClick()
        fun onBannerSkipClick()
        fun onItemNextClick(itemId: Int)
    }

    private fun HeaderViewHolder.bind(item: ShowCycleAdapterItem.Header) {
        date.text = item.date
        setting.setOnClickListener { listener?.onHeaderSettingClick() }
        title.text = item.text
    }

    private fun BannerViewHolder.bind(item: ShowCycleAdapterItem.Banner) {
        bannerImage.setImageResource(item.resId)
        nextButton.setOnClickListener { listener?.onBannerNextClick() }
        skipButton.setOnClickListener { listener?.onBannerSkipClick() }
    }

    private fun ItemHeaderViewHolder.bind(item: ShowCycleAdapterItem.ItemHeader) {
        itemCount.text = item.itemCount.toString()
    }

    private fun ItemViewHolder.bind(item: ShowCycleAdapterItem.Item) {
        image.setImageResource(item.resourceId)
        title.text = item.title
        nextCycle.text = item.nextCycleDate
        dueDate.text = item.dueDate.toString()
        nextButton.setOnClickListener { listener?.onItemNextClick(item.itemId) }
    }

    private class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.headerDate
        val setting: ImageView = itemView.headerSetting
        val title: TextView = itemView.headerTitle
    }

    private class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerImage: ImageView = itemView.bannerImage
        val nextButton: TextView = itemView.bannerNextButon
        val skipButton: TextView = itemView.bannerSkipButton
    }

    private class ItemHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCount: TextView = itemView.itemCount
    }

    private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.image
        val title: TextView = itemView.title
        val nextCycle: TextView = itemView.nextCycle
        val dueDate: TextView = itemView.dueDate
        val nextButton: ImageView = itemView.next
    }

    companion object {
        private const val VIEW_TYPE_HEADER = R.layout.item_show_cycle_header
        private const val VIEW_TYPE_BANNER = R.layout.item_show_cycle_banner
        private const val VIEW_TYPE_ITEM_HEADER = R.layout.item_show_cycle_item_header
        private const val VIEW_TYPE_ITEM = R.layout.item_show_cycle_item

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