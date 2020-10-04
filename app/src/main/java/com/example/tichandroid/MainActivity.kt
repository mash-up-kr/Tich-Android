package com.example.tichandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tichandroid.view.ui.*
import com.example.tichandroid.view.ui.items.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_continue.setOnClickListener {
            handleClickContinue()
        }
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
        val suppliesDialogFragment = SuppliesDialogFragment {
            when (it) {
                0 -> loadActivity(ShavingActivity()) // shaving
                1 -> loadActivity(ToothBrushActivity()) // tooth
                2 -> loadActivity(TowelActivity()) // towel
                3 -> loadActivity(DishClothActivity()) //dish
                4 -> loadActivity(LensActivity()) //lens
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

    private fun loadActivity(activity: Activity) {
        val intent = Intent(this@MainActivity, activity::class.java)
        startActivity(intent)
    }
}