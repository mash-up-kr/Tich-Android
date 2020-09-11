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

        btn_continue.setOnClickListener {
            clickBtn()

            Handler().postDelayed({
                originBtn()
            }, 1000)

            SuppliesFragment().apply {
                show(supportFragmentManager, SuppliesFragment.TAG)
            }
        }
    }

    private fun originBtn() {
        btn_continue.setTextColor(ContextCompat.getColor(baseContext, R.color.colorText))
        btn_continue.setBackgroundResource(R.drawable.button_border)
    }

    private fun clickBtn() {
        btn_continue.setBackgroundResource(R.drawable.button_click_border)
        btn_continue.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))

    }
}