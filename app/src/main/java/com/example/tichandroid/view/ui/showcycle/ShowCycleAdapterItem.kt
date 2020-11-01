package com.example.tichandroid.view.ui.showcycle

import com.example.tichandroid.data.model.Item
import com.mashup.android.base.extension.calculateDueDateFromToday

sealed class ShowCycleAdapterItem(val id: Int) {

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean = false

    data class Header(
        val date: String,
        val text: String
    ) : ShowCycleAdapterItem("Header-$date-$text".hashCode())

    data class Banner(
        val resId: Int
    ) : ShowCycleAdapterItem("Banner-${resId}".hashCode())

    data class ItemHeader(
        val itemCount: Int
    ) : ShowCycleAdapterItem("ItemHeader".hashCode())

    data class Item(
        val itemId: Int,
        val categoryId: Int,
        val resourceId: Int,
        val title: String,
        val nextCycleDate: String,
        val dueDate: Int,
        val cycle: Int,
        val categoryNameKey: Int
    ) : ShowCycleAdapterItem(itemId)
}

fun List<Item>.toShowCycleAdapterItems(): List<ShowCycleAdapterItem.Item> = map {
    ShowCycleAdapterItem.Item(
        itemId = it.id,
        categoryId = it.categoryId,
        resourceId = it.getResourceId(),
        title = it.title,
        nextCycleDate = it.scheduledDate,
        dueDate = calculateDueDateFromToday(it.scheduledDate),
        cycle = it.cycle,
        categoryNameKey = it.getCategoryNameKey()
    )
}
