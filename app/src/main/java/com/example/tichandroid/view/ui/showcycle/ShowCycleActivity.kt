package com.example.tichandroid.view.ui.showcycle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseActivity
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_cycle.*

@AndroidEntryPoint
class ShowCycleActivity : BaseActivity(), ShowCycleAdapter.OnClickListener {

    private val viewModel by viewModels<ShowCycleViewModel>()

    private lateinit var adapter: ShowCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cycle)
        adapter = ShowCycleAdapter(this)

        onSetUpViews()
        onBindViewModels()
    }

    private fun onSetUpViews() {
        showCycleRecycler.adapter = adapter
    }

    private fun onBindViewModels() {
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