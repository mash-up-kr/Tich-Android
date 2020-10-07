package com.example.tichandroid.view.ui.items

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tichandroid.R
import com.example.tichandroid.util.RxEventBus
import com.example.tichandroid.view.ui.CycleDialogFragment
import com.example.tichandroid.view.ui.StartDateDialogFragment
import com.example.tichandroid.view.ui.SuppliesDialogFragment
import com.example.tichandroid.view.ui.showcycle.ShowCycleActivity
import com.example.tichandroid.viewmodel.ShavingViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mashup.android.base.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dish_cloth.*
import kotlinx.android.synthetic.main.fragment_shaving.btnEnroll
import kotlinx.android.synthetic.main.fragment_shaving.editTitle
import kotlinx.android.synthetic.main.fragment_shaving.imgCalender
import kotlinx.android.synthetic.main.fragment_shaving.imgPeriod
import kotlinx.android.synthetic.main.fragment_shaving.imgStart
import kotlinx.android.synthetic.main.fragment_shaving.imgTitle
import kotlinx.android.synthetic.main.fragment_shaving.txtChoice
import kotlinx.android.synthetic.main.fragment_shaving.txtDate
import kotlinx.android.synthetic.main.fragment_shaving.txtPeriod
import kotlinx.android.synthetic.main.fragment_shaving.txtStart
import kotlinx.android.synthetic.main.fragment_shaving.txtTitle
import kotlinx.android.synthetic.main.cycle_bottom_sheet.*
import kotlinx.android.synthetic.main.date_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class DishClothFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_dish_cloth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // block back button
                }
            })

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

        dish_cloth_back.setOnClickListener {
            removeFragment()

            val suppliesDialogFragment = SuppliesDialogFragment {
                when (it) {
                    0 -> loadFragment(ShavingFragment())
                    1 -> loadFragment(ToothBrushFragment())
                    2 -> loadFragment(TowelFragment())
                    3 -> loadFragment(DishClothFragment())
                    4 -> loadFragment(LensFragment())
                }
            }
            suppliesDialogFragment.show(
                requireActivity().supportFragmentManager,
                SuppliesDialogFragment.TAG
            )
        }

        btnEnroll.setOnClickListener {

            if (editTitle.text.isNullOrEmpty()) {
                requireContext().showToast(R.string.empty_title)
            } else {
                clickBtn()

                Handler().postDelayed(::originBtn, 1000)

                viewModel.saveItem(
                    txt_dish_cloth.text.toString(),
                    choiceValue,
                    txtDate.text.toString(),
                    editTitle.text.toString()
                )

                val intent = Intent(requireContext(), ShowCycleActivity::class.java)
                startActivity(intent)
            }
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

        dateSheetBehavior = BottomSheetBehavior.from(date_bottomSheet)
        cycleSheetBehavior = BottomSheetBehavior.from(cycle_bottomSheet)
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