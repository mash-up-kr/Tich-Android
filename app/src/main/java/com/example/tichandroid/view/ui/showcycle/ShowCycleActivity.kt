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
class ShowCycleActivity : BaseActivity() {

    private val viewModel by viewModels<ShowCycleViewModel>()

    private lateinit var adapter: ShowCycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cycle)

        onSetUpViews()
        onBindViewModels()
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

    private fun onSetUpViews() {
        showCycleRecycler.adapter = adapter
    }
}