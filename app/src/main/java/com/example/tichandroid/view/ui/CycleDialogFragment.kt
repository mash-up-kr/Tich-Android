package com.example.tichandroid.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mashup.android.base.extension.showToast
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*


class CycleDialogFragment : BottomSheetDialogFragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgCycleClose.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        btnEveryDay.setOnClickListener {
            clickEveryDay()
            txt_above_thumb.visibility = View.GONE
        }

        btnEveryWeek.setOnClickListener {
            clickEveryWeek()
            txt_above_thumb.visibility = View.GONE
        }

        btnEveryMonth.setOnClickListener {
            clickEveryMonth()
            txt_above_thumb.visibility = View.GONE
        }

        btnCycleConfirm.setOnClickListener {

            if (seek_bar.progress == 0) {
                context?.showToast(R.string.empty_cycle)

            } else {
                clickBtn()

                RxEventBus.setCycle(txt_above_thumb.text.toString())

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
            }
        }
    }

    private fun clickEveryDay() {
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left_click)
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center)
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right)

        txt_first_date.setText(R.string.day_min)
        txt_last_date.setText(R.string.day_max)

        seek_bar.progress = 0
        seek_bar.max = 31

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txt_above_thumb.visibility = View.VISIBLE
                val value = seekBar?.progress
                txt_above_thumb.text = "${value}일"

                if (value != 0) {
                    txt_above_thumb.x = seekBar!!.thumb.bounds.left.toFloat()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun clickEveryWeek() {
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center_click)
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left)
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right)

        txt_first_date.setText(R.string.week_min)
        txt_last_date.setText(R.string.week_max)

        seek_bar.progress = 0
        seek_bar.max = 8

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txt_above_thumb.visibility = View.VISIBLE
                val value = seekBar?.progress
                txt_above_thumb.text = "${value}주"

                if (value != 0) {
                    txt_above_thumb.x = seekBar!!.thumb.bounds.left.toFloat()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

    }

    private fun clickEveryMonth() {
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right_click)
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center)
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left)

        txt_first_date.setText(R.string.month_min)
        txt_last_date.setText(R.string.month_max)

        seek_bar.progress = 0
        seek_bar.max = 12

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txt_above_thumb.visibility = View.VISIBLE
                val value = seekBar?.progress
                txt_above_thumb.text = "${value}달"

                if (value != 0) {
                    txt_above_thumb.x = seekBar!!.thumb.bounds.left.toFloat()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun clickBtn() {
        btnCycleConfirm.apply {
            setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            setBackgroundResource(R.drawable.button_click_border)
        }
    }
}