package com.example.tichandroid.util

import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.fragment_item.*
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val chosenDate = calendar.time
    val startDateFormat = SimpleDateFormat("yyyy.MM.dd.(EE)")
    return startDateFormat.format(chosenDate)
}
