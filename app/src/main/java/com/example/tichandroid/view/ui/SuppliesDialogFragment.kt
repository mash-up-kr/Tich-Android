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
            removeFragment()
        }

        img_shaving_choice.setOnClickListener {
            itemClick(0)
            removeFragment()
        }

        img_tooth_choice.setOnClickListener {
            itemClick(1)
            removeFragment()
        }
        img_shower_choice.setOnClickListener {
            itemClick(2)
            removeFragment()
        }
        img_dish_choice.setOnClickListener {
            itemClick(3)
            removeFragment()
        }
        img_lens_choice.setOnClickListener {
            itemClick(4)
            removeFragment()
        }
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }

    private fun removeFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
    }
}
