package com.example.tichandroid

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.tichandroid.view.ui.SuppliesFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.supplies_bottom_sheet.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        btn_continue.setOnClickListener { handleClickContinue() }
    }

    private fun originBtn() {
        btn_continue.apply {
            setTextColor(ContextCompat.getColor(baseContext, R.color.colorText))
            setBackgroundResource(R.drawable.button_border)
        }
    }

    private fun handleClickContinue() {
        btn_continue.apply {
            setBackgroundResource(R.drawable.button_click_border)
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorWhite))
        }
        Handler().postDelayed(::originBtn, 1000)
        SuppliesFragment().show(supportFragmentManager, SuppliesFragment.TAG)
    }
}