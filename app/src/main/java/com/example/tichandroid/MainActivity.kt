package com.example.tichandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tichandroid.view.ui.SuppliesDialogFragment
import com.example.tichandroid.view.ui.items.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_continue.setOnClickListener { handleClickContinue() }
    }

    private fun originBtn() {
        btn_continue.apply {
            setTextColor(ContextCompat.getColor(baseContext, R.color.colorCaption))
            setBackgroundResource(R.drawable.button_border)
        }
    }

    private fun handleClickContinue() {

        btn_continue.apply {
            setBackgroundResource(R.drawable.button_click_border)
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorWhite))
        }
        Handler().postDelayed(::originBtn, 1000)

        val suppliesDialogFragment = SuppliesDialogFragment {
            when (it) {
                0 -> loadFragment(ShavingFragment())
                1 -> loadFragment(ToothBrushFragment())
                2 -> loadFragment(TowelFragment())
                3 -> loadFragment(DishClothFragment())
                4 -> loadFragment(LensFragment())
            }
        }
        suppliesDialogFragment.show(supportFragmentManager, SuppliesDialogFragment.TAG)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_items, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}