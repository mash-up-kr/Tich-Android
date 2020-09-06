package com.example.tichandroid.view.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_shaving.*
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.startdate_bottom_sheet.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShavingActivity : AppCompatActivity() {

    private lateinit var dateSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cycleSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shaving)

        initNow()

        imgBack.setOnClickListener { finish() }

        imgTitle.setOnClickListener {
            clickTitle()
        }

        imgStart.setOnClickListener {
            clickStart()
        }

        imgPeriod.setOnClickListener {
            clickPeriod()
        }

        btnEnroll.setOnClickListener {
            clickBtn()

            Handler().postDelayed({
                originBtn()
            }, 2000)
        }

        imgCalender.setOnClickListener {
            StartDateFragment().apply {
                show(supportFragmentManager, StartDateFragment.TAG)
            }
        }

        txtChoice.setOnClickListener {
            CycleFragment().apply {
                show(supportFragmentManager, CycleFragment.TAG)
            }
        }
        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initNow() {
        val now = LocalDate.now()
        val nowDate = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.(EE)"))

        txtDate.text = nowDate
    }

    private fun clickTitle() {
        imgTitle.setBackgroundResource(R.drawable.title_blue_border)
        txtTitle.setTextColor(ContextCompat.getColor(baseContext, R.color.colorBlue))

        imgStart.setBackgroundResource(R.drawable.title_border)
        txtStart.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))

        imgPeriod.setBackgroundResource(R.drawable.title_border)
        txtPeriod.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))
    }

    private fun clickStart() {
        imgStart.setBackgroundResource(R.drawable.title_blue_border)
        txtStart.setTextColor(ContextCompat.getColor(baseContext, R.color.colorBlue))

        imgTitle.setBackgroundResource(R.drawable.title_border)
        txtTitle.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))

        imgPeriod.setBackgroundResource(R.drawable.title_border)
        txtPeriod.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))
    }

    private fun clickPeriod() {
        imgPeriod.setBackgroundResource(R.drawable.title_blue_border)
        txtPeriod.setTextColor(ContextCompat.getColor(baseContext, R.color.colorBlue))

        imgStart.setBackgroundResource(R.drawable.title_border)
        txtStart.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))

        imgTitle.setBackgroundResource(R.drawable.title_border)
        txtTitle.setTextColor(ContextCompat.getColor(baseContext, R.color.colorGray))
    }


    private fun originBtn() {
        btnEnroll.setTextColor(ContextCompat.getColor(baseContext, R.color.colorText))
        btnEnroll.setBackgroundResource(R.drawable.button_border)
    }

    private fun clickBtn() {
        btnEnroll.setTextColor(ContextCompat.getColor(baseContext, R.color.colorWhite))
        btnEnroll.setBackgroundResource(R.drawable.button_click_border)
    }
}