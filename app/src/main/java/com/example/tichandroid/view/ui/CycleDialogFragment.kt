package com.example.tichandroid.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mashup.android.base.extension.showToast
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*

class CycleDialogFragment : BottomSheetDialogFragment() {

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
            handleEveryDayClick()
        }

        btnEveryWeek.setOnClickListener {
            handleEveryWeekClick()
        }

        btnEveryMonth.setOnClickListener {
            handleEveryMonthClick()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtAboveThumb.isVisible = true
                val value = seekBar?.progress

                when (seekBar?.max) {
                    31 -> txtAboveThumb.text = "${value}일"
                    8 -> txtAboveThumb.text = "${value}주"
                    12 -> txtAboveThumb.text = "${value}달"
                }

                if (value != 0) {
                    if (seekBar != null) {
                        txtAboveThumb.x = seekBar.thumb.bounds.left.toFloat()
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        btnCycleConfirm.setOnClickListener {

            if (seekBar.progress == 0) {
                context?.showToast(R.string.empty_cycle)

            } else {
                clickBtn()

                RxEventBus.setCycle(txtAboveThumb.text.toString())

                dismiss()
            }
        }
    }

    private fun handleEveryDayClick() {
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left_click)
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center)
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right)

        txtFirstDate.setText(R.string.day_min)
        txtLastDate.setText(R.string.day_max)

        seekBar.progress = 0
        seekBar.max = 31

        txtAboveThumb.isVisible = false
    }

    private fun handleEveryWeekClick() {
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center_click)
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left)
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right)

        txtFirstDate.setText(R.string.week_min)
        txtLastDate.setText(R.string.week_max)

        seekBar.progress = 0
        seekBar.max = 8

        txtAboveThumb.isVisible = false
    }

    private fun handleEveryMonthClick() {
        btnEveryMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEveryMonth.setBackgroundResource(R.drawable.cycle_border_right_click)
        btnEveryWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryWeek.setBackgroundResource(R.drawable.cycle_border_center)
        btnEveryDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        btnEveryDay.setBackgroundResource(R.drawable.cycle_border_left)

        txtFirstDate.setText(R.string.month_min)
        txtLastDate.setText(R.string.month_max)

        seekBar.progress = 0
        seekBar.max = 12

        txtAboveThumb.isVisible = false
    }

    private fun clickBtn() {
        btnCycleConfirm.apply {
            setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            setBackgroundResource(R.drawable.button_click_border)
        }
    }

    companion object {
        const val TAG = "CycleFragment"
    }
}