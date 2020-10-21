package com.example.tichandroid.view.ui.items

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseViewModelFragment
import com.example.tichandroid.util.RxEventBus
import com.example.tichandroid.view.ui.CycleDialogFragment
import com.example.tichandroid.view.ui.StartDateDialogFragment
import com.example.tichandroid.view.ui.SuppliesDialogFragment
import com.example.tichandroid.view.ui.showcycle.ShowCycleActivity
import com.example.tichandroid.viewmodel.ShavingViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import com.mashup.android.base.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.date_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_shaving.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class ItemFragment(private val layoutId: Int) : BaseViewModelFragment() {

    private val viewModel by viewModels<ShavingViewModel>()

    private lateinit var dateSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cycleSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var result: List<String>
    private var choiceValue by Delegates.notNull<Int>()
    private lateinit var choiceForm: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onBindViewModels() {
        btnEnroll.setOnClickListener {
            if (editTitle.text.isNullOrEmpty()) {
                requireContext().showToast(R.string.empty_title)
            } else {
                clickBtn()

                Handler().postDelayed(::originBtn, 1000)

                viewModel.saveItem(
                    txtItem.text.toString(),
                    choiceValue,
                    txtDate.text.toString(),
                    editTitle.text.toString()
                ).observeOnMain()
                    .subscribeWithErrorLogger {
                        val intent = Intent(requireContext(), ShowCycleActivity::class.java)
                        startActivity(intent)
                    }
                    .addToDisposables()
            }
        }
    }

    override fun onSetupViews(view: View) {
        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)

        initNow()

        imgTitle.setOnClickListener {
            clickTitle()
        }

        imgStart.setOnClickListener {
            clickStart()
        }

        imgPeriod.setOnClickListener {
            clickPeriod()
        }

        imgBack.setOnClickListener {
            removeFragment()

            SuppliesDialogFragment {
                when (it) {
                    0 -> loadFragment(ItemFragment(R.layout.fragment_shaving))
                    1 -> loadFragment(ItemFragment(R.layout.fragment_tooth_brush))
                    2 -> loadFragment(ItemFragment(R.layout.fragment_towel))
                    3 -> loadFragment(ItemFragment(R.layout.fragment_dish_cloth))
                    4 -> loadFragment(ItemFragment(R.layout.fragment_lens))
                }
            }.show(requireActivity().supportFragmentManager, SuppliesDialogFragment.TAG)
        }

        imgCalender.setOnClickListener {
            clickStart()

            StartDateDialogFragment().show(
                requireActivity().supportFragmentManager,
                StartDateDialogFragment.TAG
            )
        }

        imgSetPeriod.setOnClickListener {
            clickPeriod()

            CycleDialogFragment().show(
                requireActivity().supportFragmentManager,
                CycleDialogFragment.TAG
            )
        }
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        RxEventBus.subjectSetDate.subscribe {
            txtDate.text = it
        }

        RxEventBus.subjectSetCycle.subscribe {
            txtChoice.text = it

            result = txtChoice.text.split("")
            choiceValue = result[1].toInt()
            choiceForm = result[2]

            when (choiceForm) {
                "주" -> choiceValue *= 7
                "달" -> choiceValue *= 30
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun initNow() {
        val sdf = SimpleDateFormat("yyyy.MM.dd.(EE)")
        val currentDate = sdf.format(Date())

        txtDate.text = currentDate
    }

    private fun clickTitle() {
        imgTitle.setBackgroundResource(R.drawable.title_blue_border)
        txtTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlue))

        imgStart.setBackgroundResource(R.drawable.title_border)
        txtStart.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgPeriod.setBackgroundResource(R.drawable.title_border)
        txtPeriod.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgCalender.setImageResource(R.drawable.ic_date_gray)
        imgSetPeriod.setImageResource(R.drawable.ic_toggle)
    }

    private fun clickStart() {
        imgStart.setBackgroundResource(R.drawable.title_blue_border)
        txtStart.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlue))

        imgTitle.setBackgroundResource(R.drawable.title_border)
        txtTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgPeriod.setBackgroundResource(R.drawable.title_border)
        txtPeriod.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgCalender.setImageResource(R.drawable.ic_date_blue)
        imgSetPeriod.setImageResource(R.drawable.ic_toggle)
    }

    private fun clickPeriod() {
        imgPeriod.setBackgroundResource(R.drawable.title_blue_border)
        txtPeriod.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlue))

        imgStart.setBackgroundResource(R.drawable.title_border)
        txtStart.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgTitle.setBackgroundResource(R.drawable.title_border)
        txtTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))

        imgCalender.setImageResource(R.drawable.ic_date_gray)
        imgSetPeriod.setImageResource(R.drawable.ic_select_blue)
    }

    private fun originBtn() {
        btnEnroll.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorCaption))
        btnEnroll.setBackgroundResource(R.drawable.button_border)
    }

    private fun clickBtn() {
        btnEnroll.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        btnEnroll.setBackgroundResource(R.drawable.button_click_border)
    }

    private fun removeFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_items, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}