package com.example.tichandroid.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.date_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*

class StartDateDialogFragment : BottomSheetDialogFragment() {

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.date_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgDateClose.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        btnDateConfirm.setOnClickListener {
            val selectedDate = formatDate(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            RxEventBus.setDate(selectedDate)

            handleConfirmClick()

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(year: Int, month: Int, day: Int): String {
        calendar.set(year, month, day)
        val chosenDate = calendar.time
        val startDateFormat = SimpleDateFormat("yyyy.MM.dd.(EE)")
        return startDateFormat.format(chosenDate)
    }

    private fun handleConfirmClick() {
        btnDateConfirm.apply {
            setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            setBackgroundResource(R.drawable.button_click_border)
        }
    }

    companion object {
        const val TAG = "StartDateFragment"
    }
}
