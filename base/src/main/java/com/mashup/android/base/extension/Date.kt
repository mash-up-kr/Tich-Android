package com.mashup.android.base.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun calculateDueDateFromToday(scheduledDate: String): Int {
    val format = SimpleDateFormat("dd").format(Date())
    val targetDay = scheduledDate.split("-").last()
    return targetDay.toInt() - format.toInt()
}

@SuppressLint("SimpleDateFormat")
fun getTodayDateByFormat(format: String): String = SimpleDateFormat(format).format(Date())