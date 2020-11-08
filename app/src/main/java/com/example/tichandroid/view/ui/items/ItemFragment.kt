package com.example.tichandroid.view.ui.items

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseViewModelFragment
import com.example.tichandroid.util.RxEventBus
import com.example.tichandroid.view.ui.CycleDialogFragment
import com.example.tichandroid.view.ui.StartDateDialogFragment
import com.example.tichandroid.viewmodel.ShavingViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mashup.android.base.extension.getIntOrNull
import com.mashup.android.base.extension.inflate
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import com.mashup.android.base.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.date_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_item.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class ItemFragment : BaseViewModelFragment() {

    private val viewModel by viewModels<ShavingViewModel>()

    private lateinit var dateSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var cycleSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var result: List<String>
    private var choiceValue by Delegates.notNull<Int>()
    private lateinit var choiceForm: String

    private lateinit var date: List<String>
    private lateinit var dateResult: String

    private val itemName by lazy {
        arguments?.getIntOrNull(KEY_ITEM_NAME_RESOURCE_ID)?.let { getString(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_item)

    override fun onSetupViews() {
        itemName?.let { txtItem.text = it }

        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)

        initNow()

        imgTitle.setOnClickListener { clickTitle() }
        imgStart.setOnClickListener { clickStart() }
        imgPeriod.setOnClickListener { clickPeriod() }
        imgBack.setOnClickListener {
            removeFragment()
//            SuppliesDialogFragment {
//                when (it) {
//                    0 -> getString(R.string.shaving).loadFragment()
//                    1 -> getString(R.string.tooth_brush).loadFragment()
//                    2 -> getString(R.string.shower_tower).loadFragment()
//                    3 -> getString(R.string.dish_cloth).loadFragment()
//                    4 -> getString(R.string.lens).loadFragment()
//                }
//            }.show(requireActivity().supportFragmentManager, SuppliesDialogFragment.TAG)
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

        btnEnroll.setOnClickListener {
            when {
                editTitle.text.isNullOrEmpty() -> {
                    requireContext().showToast(R.string.empty_title)
                }
                txtChoice.text == getString(R.string.exchange_period_hint) -> {
                    requireContext().showToast(R.string.empty_cycle)
                }
                else -> {
                    clickBtn()

                    //Handler().postDelayed(::originBtn, 1000)

                    viewModel.saveItem(
                        txtItem.text.toString(),
                        choiceValue,
                        dateResult,
                        editTitle.text.toString()
                    ).observeOnMain()
                        .subscribeWithErrorLogger {
                            removeFragment()
                        }
                        .addToDisposables()
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        RxEventBus.subjectSetDate.subscribe {
            txtDate.text = it

            date = txtDate.text.split(".")
            dateResult = date[0] + "-" + date[1] + "-" + date[2]

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

//    private fun String.loadFragment() {
//        requireActivity().supportFragmentManager.beginTransaction().replace(
//            R.id.fragment_items, ItemFragment().apply {
//                arguments = Bundle().apply {
//                    putString(itemName, this@loadFragment)
//                }
//            }
//        ).addToBackStack(null).commit()
//    }

    companion object {
        private const val KEY_ITEM_NAME_RESOURCE_ID = "key_item_name"

        fun getInstance(itemType: Int): ItemFragment {
            val itemText = when (itemType) {
                0 -> R.string.shaving
                1 -> R.string.tooth_brush
                2 -> R.string.shower_tower
                3 -> R.string.dish_cloth
                else -> R.string.lens
            }
            return ItemFragment().apply {
                arguments = bundleOf(
                    KEY_ITEM_NAME_RESOURCE_ID to itemText
                )
            }
        }
    }
}