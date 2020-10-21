package com.example.tichandroid.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.example.tichandroid.util.formatDate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.date_bottom_sheet.*

class StartDateDialogFragment : BottomSheetDialogFragment() {

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
