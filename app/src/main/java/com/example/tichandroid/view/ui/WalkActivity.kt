package com.example.tichandroid.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tichandroid.R
import com.example.tichandroid.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_walk.*

class WalkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk)

        view_pager.adapter = ViewPagerAdapter()
    }
}