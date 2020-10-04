package com.example.tichandroid.view.ui.showcycle

import com.example.tichandroid.R
import com.example.tichandroid.data.model.Item

sealed class ShowCycleAdapterItem(val id: Int) {

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean = false

    data class Header(
        val date: String,
        val textId: Int
    ) : ShowCycleAdapterItem("Header-$date".hashCode())

    data class Banner(
        val text: String,
        val resId: Int
    ) : ShowCycleAdapterItem("Banner-${text}".hashCode())

    data class ItemHeader(
        val itemCount: Int
    ) : ShowCycleAdapterItem("ItemHeader".hashCode())

    data class Item(
        val itemId: Int,
        val resId: Int,
        val title: String,
        val nextCycleDate: String,
        val dueDate: String,
        val cycle: String
    ) : ShowCycleAdapterItem(itemId)
}

fun List<Item>.toShowCycleAdapterItems(): List<ShowCycleAdapterItem> {
    return listOf(ShowCycleAdapterItem.Banner("Sample Text", R.drawable.ic_close))
}
