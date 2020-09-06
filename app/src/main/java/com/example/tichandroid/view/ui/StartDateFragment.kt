package com.example.tichandroid.view.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import com.example.tichandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.startdate_bottom_sheet.*
import java.text.DateFormat
import java.util.*

class StartDateFragment : BottomSheetDialogFragment() {

    private lateinit var calendar: Calendar

    companion object {
        const val TAG = "StartDateFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.startdate_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        img_date_close.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        btn_confirm.setOnClickListener {
            clickBtn()

            Handler().postDelayed({
                originBtn()
            }, 2000)
        }
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