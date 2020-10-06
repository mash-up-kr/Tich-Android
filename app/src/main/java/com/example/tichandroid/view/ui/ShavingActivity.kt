package com.example.tichandroid.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.example.tichandroid.RxEventBus
import com.example.tichandroid.view.ui.showcycle.ShowCycleActivity
import com.example.tichandroid.viewmodel.ShavingViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shaving.*
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.startdate_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ShavingActivity : AppCompatActivity() {

    private val viewModel by viewModels<ShavingViewModel>()

    private lateinit var dateSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cycleSheetBehavior: BottomSheetBehavior<ConstraintLayout>

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

            val intent = Intent(this, ShowCycleActivity::class.java)
            startActivity(intent)

            Handler().postDelayed({
                originBtn()
            }, 2000)
        }

        imgCalender.setOnClickListener {
            StartDateFragment().apply {
                show(supportFragmentManager, StartDateFragment.TAG)
            }
        }

        img_set_period.setOnClickListener {
            CycleFragment().apply {
                show(supportFragmentManager, CycleFragment.TAG)
            }
        }
        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)
    }

    override fun onResume() {
        super.onResume()

        RxEventBus.subjectSetDate.subscribe {
            txtDate.text = it
        }

        RxEventBus.subjectSetCycle.subscribe {
            txtChoice.text = it
        }
    }


    private fun initNow() {
        val sdf = SimpleDateFormat("yyyy.MM.dd.(EE)")
        val currentDate = sdf.format(Date())

        txtDate.text = currentDate

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
        btnEnroll.setTextColor(ContextCompat.getColor(baseContext, R.color.colorCaption))
        btnEnroll.setBackgroundResource(R.drawable.button_border)
    }

    private fun clickBtn() {
        btnEnroll.setTextColor(ContextCompat.getColor(baseContext, R.color.colorWhite))
        btnEnroll.setBackgroundResource(R.drawable.button_click_border)
    }
}