package com.example.tichandroid.view.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*


class CycleFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CycleFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cycle_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        img_cycle_close.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        btn_everyday.setOnClickListener {
            clickEveryDay()
        }
        btn_everyweek.setOnClickListener {
            clickEveryWeek()
        }
        btn_everymonth.setOnClickListener {
            clickEveryMonth()
        }

        seek_bar.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
        btn_confirm.setOnClickListener {
            clickBtn()

            Handler().postDelayed({
                originBtn()
            }, 2000)
        }
    }

    private fun clickEveryDay() {
        btn_everyday.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        btn_everyday.setBackgroundResource(R.drawable.cycle_border_left_click)
        btn_everyweek.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everyweek.setBackgroundResource(R.drawable.cycle_border_center)
        btn_everymonth.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everymonth.setBackgroundResource(R.drawable.cycle_border_right)
    }

    private fun clickEveryWeek() {
        btn_everyweek.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        btn_everyweek.setBackgroundResource(R.drawable.cycle_border_center_click)
        btn_everyday.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everyday.setBackgroundResource(R.drawable.cycle_border_left)
        btn_everymonth.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everymonth.setBackgroundResource(R.drawable.cycle_border_right)
    }

    private fun clickEveryMonth() {
        btn_everymonth.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        btn_everymonth.setBackgroundResource(R.drawable.cycle_border_right_click)
        btn_everyweek.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everyweek.setBackgroundResource(R.drawable.cycle_border_center)
        btn_everyday.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        btn_everyday.setBackgroundResource(R.drawable.cycle_border_left)
    }

    private fun originBtn() {
        btn_confirm.visibility = View.VISIBLE
        btn_confirm_click.visibility = View.GONE
    }

    private fun clickBtn() {
        btn_confirm.visibility = View.GONE
        btn_confirm_click.visibility = View.VISIBLE
    }
}