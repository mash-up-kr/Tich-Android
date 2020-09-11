package com.example.tichandroid.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.tichandroid.R
import com.example.tichandroid.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_walk.*

class WalkActivity : AppCompatActivity() {
    private var currentViewPager = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk)

        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.offscreenPageLimit = 3
        dots_indicator.attachViewPager(view_pager)

        txt_walk_skip.setOnClickListener {
            view_pager.currentItem = 4
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 3) {
                    hideFunc()
                } else {
                    showFunc()
                }
            }
        })

        btn_walk_next.setOnClickListener {
            val current = view_pager.currentItem
            if (current == 4) {
                view_pager.setCurrentItem(0, true)
            } else {
                view_pager.setCurrentItem(current + 1, true)
            }
        }
    }

    private fun showFunc() {
        txt_walk_skip.visibility = View.VISIBLE
        btn_walk_next.visibility = View.VISIBLE
        dots_indicator.visibility = View.VISIBLE
    }

    private fun hideFunc() {
        txt_walk_skip.visibility = View.GONE
        btn_walk_next.visibility = View.GONE
        dots_indicator.visibility = View.GONE
    }
}