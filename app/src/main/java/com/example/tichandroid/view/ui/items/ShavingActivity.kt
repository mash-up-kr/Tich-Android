package com.example.tichandroid.view.ui.items

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.example.tichandroid.view.ui.CycleDialogFragment
import com.example.tichandroid.view.ui.StartDateDialogFragment
import com.example.tichandroid.view.ui.showcycle.ShowCycleActivity
import com.example.tichandroid.viewmodel.ShavingViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mashup.android.base.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shaving.*
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.date_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class ShavingActivity : AppCompatActivity() {

    private val viewModel by viewModels<ShavingViewModel>()

    private lateinit var dateSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cycleSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var result: List<String>
    private var choiceValue by Delegates.notNull<Int>()
    private lateinit var choiceForm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shaving)

        initNow()

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

            Handler().postDelayed(::originBtn, 1000)

             viewModel.saveItem(
                 txtShaving.text.toString(),
                 choiceValue,
                 txtDate.text.toString(),
                 editTitle.text.toString()
             )

             val intent = Intent(this, ShowCycleActivity::class.java)
             startActivity(intent)
        }


        imgCalender.setOnClickListener {
            StartDateDialogFragment().apply {
                show(supportFragmentManager, StartDateDialogFragment.TAG)
            }
        }

        img_set_period.setOnClickListener {
            CycleDialogFragment().apply {
                show(supportFragmentManager, CycleDialogFragment.TAG)
            }
        }

        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        RxEventBus.subjectSetDate.subscribe {
            txtDate.text = it
        }

        RxEventBus.subjectSetCycle.subscribe {
            txtChoice.text = it

            result = txtChoice.text.split("")
            choiceValue = result[1].toInt()
            choiceForm = result[2]

            when (choiceForm) {
                "주" -> choiceValue *= 7
                "달" -> choiceValue *= 30
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
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