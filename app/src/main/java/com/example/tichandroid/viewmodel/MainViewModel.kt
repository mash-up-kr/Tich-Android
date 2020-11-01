package com.example.tichandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.processors.BehaviorProcessor

class MainViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val nameProcessor = BehaviorProcessor.createDefault("")
    val name = nameProcessor

    fun setName(name: String?) {
        name?.let { nameProcessor.offer(it) }
    }
}