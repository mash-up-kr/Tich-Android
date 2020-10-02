package com.example.tichandroid.data.model

data class Item(
    val id: Int,
    val userId: String,
    val categoryId: String,
    val title: String,
    val startDate: String,
    val latestDate: String,
    val scheduledDate: String,
    val cycle: String
)