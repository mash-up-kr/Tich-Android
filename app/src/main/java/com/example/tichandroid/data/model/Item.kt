package com.example.tichandroid.data.model

import com.example.tichandroid.R

data class Item(
    val id: Int,
    val userId: Int,
    val categoryId: Int,
    val title: String,
    val startDate: String,
    val latestDate: String,
    val scheduledDate: String,
    val cycle: Int
) {

    fun getResourceId(): Int = when (categoryId) {
        1 -> R.drawable.ic_shaving
        2 -> R.drawable.ic_brushteeth
        3 -> R.drawable.ic_towel
        4 -> R.drawable.ic_dishcloth
        else -> R.drawable.ic_lens
    }

    fun getCategoryNameKey(): Int = when (categoryId) {
        1 -> R.string.shaving
        2 -> R.string.tooth_brush
        3 -> R.string.shower_tower
        4 -> R.string.dish_cloth
        else -> R.string.lens
    }
}