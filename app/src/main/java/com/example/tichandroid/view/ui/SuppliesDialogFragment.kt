package com.example.tichandroid.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tichandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.supplies_bottom_sheet.*

class SuppliesDialogFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.supplies_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_choice_close.setOnClickListener {
            dismiss()
        }

        img_shaving_choice.setOnClickListener {
            itemClick(0)
            dismiss()
        }

        img_tooth_choice.setOnClickListener {
            itemClick(1)
            dismiss()
        }
        img_shower_choice.setOnClickListener {
            itemClick(2)
            dismiss()
        }
        img_dish_choice.setOnClickListener {
            itemClick(3)
            dismiss()
        }
        img_lens_choice.setOnClickListener {
            itemClick(4)
            dismiss()
        }
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }
}
