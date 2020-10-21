package com.example.tichandroid

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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
                0 -> loadFragment(ItemFragment(R.layout.fragment_shaving))
                1 -> loadFragment(ItemFragment(R.layout.fragment_tooth_brush))
                2 -> loadFragment(ItemFragment(R.layout.fragment_towel))
                3 -> loadFragment(ItemFragment(R.layout.fragment_dish_cloth))
                4 -> loadFragment(ItemFragment(R.layout.fragment_lens))
            }
        }.show(supportFragmentManager, SuppliesDialogFragment.TAG)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.commit {
            transaction.replace(R.id.fragment_items, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}