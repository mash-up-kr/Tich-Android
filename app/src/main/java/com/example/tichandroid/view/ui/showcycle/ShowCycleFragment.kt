package com.example.tichandroid.view.ui.showcycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseViewModelFragment
import com.example.tichandroid.view.ui.SuppliesDialogFragment
import com.mashup.android.base.extension.inflate
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import kotlinx.android.synthetic.main.fragment_show_cycle.*

class ShowCycleFragment : BaseViewModelFragment(), ShowCycleAdapter.OnClickListener {

    private val viewModel: ShowCycleViewModel by activityViewModels()

    private lateinit var adapter: ShowCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ShowCycleAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_show_cycle)

    override fun onSetupViews() {
        showCycleRecycler.adapter = adapter
        showCycleFab.setOnClickListener {
            SuppliesDialogFragment {
                (requireActivity() as? ShowCycleContainer)?.showItemManager(it)
            }.show(childFragmentManager, "supplies-dialog")
        }
    }

    override fun onBindViewModels() {
        viewModel.isEmpty
            .observeOnMain()
            .subscribeWithErrorLogger {
                emptyContainer.isVisible = it
                emptySetting.isVisible = it
                showCycleRecycler.isVisible = !it
            }
            .addToDisposables()

        viewModel.isInitialLoading
            .observeOnMain()
            .subscribeWithErrorLogger {
                loadingView.isVisible = it
            }
            .addToDisposables()

        viewModel.items
            .observeOnMain()
            .subscribeWithErrorLogger {
                adapter.submitList(it)
            }
            .addToDisposables()
    }

    private fun navigateItemManager() {

    }

    override fun onHeaderSettingClick() {
        TODO("Not yet implemented")
    }

    override fun onBannerNextClick() {
        TODO("Not yet implemented")
    }

    override fun onBannerSkipClick() {
        TODO("Not yet implemented")
    }

    override fun onItemNextClick(itemId: Int) {
        // Item에 맞는 Fragment로 이동
    }
}