package com.example.tichandroid

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tichandroid.view.ui.SuppliesDialogFragment
import com.example.tichandroid.view.ui.items.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO : userName

        btnContinue.setOnClickListener { handleClickContinue() }
    }

    private fun originBtn() {
        btnContinue.apply {
            setTextColor(ContextCompat.getColor(baseContext, R.color.colorCaption))
            setBackgroundResource(R.drawable.button_border)
        }
    }

    private fun handleClickContinue() {
        btnContinue.apply {
            setBackgroundResource(R.drawable.button_click_border)
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorWhite))
        }
        Handler().postDelayed(::originBtn, 1000)

        SuppliesDialogFragment {
            when (it) {
                0 -> getString(R.string.shaving).loadFragment()
                1 -> getString(R.string.tooth_brush).loadFragment()
                2 -> getString(R.string.shower_tower).loadFragment()
                3 -> getString(R.string.dish_cloth).loadFragment()
                4 -> getString(R.string.lens).loadFragment()
            }
        }.show(supportFragmentManager, SuppliesDialogFragment.TAG)
    }

    private fun String.loadFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_items, ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ItemFragment.itemName, this@loadFragment)
                }
            }
        ).addToBackStack(null).commit()
    }
}