package com.example.tichandroid.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tichandroid.R
import kotlinx.android.synthetic.main.activity_show_cycle.*
import java.text.SimpleDateFormat
import java.util.*

class ShowCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cycle)

        val sdf = SimpleDateFormat("MM/dd")
        val currentDate = sdf.format(Date())

        txt_show_day.text = currentDate
    }
}