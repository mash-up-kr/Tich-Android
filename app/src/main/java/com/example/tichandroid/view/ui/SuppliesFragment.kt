package com.example.tichandroid.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tichandroid.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mashup.android.base.extension.inflate
import kotlinx.android.synthetic.main.supplies_bottom_sheet.*

class SuppliesFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(inflater, R.layout.supplies_bottom_sheet)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_choice_close.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }
        img_shaving_choice.setOnClickListener {
            startActivity(Intent(requireContext(), ShavingActivity::class.java))
        }
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }
}