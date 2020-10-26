package com.example.tichandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.ItemRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.processors.BehaviorProcessor

class ShavingViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val itemRepository: ItemRepository
) : BaseViewModel(schedulerProvider) {

    private val isInitialLoadingProcessor = BehaviorProcessor.createDefault(true)

    fun saveItem(
        category: String,
        cycle: Int,
        startDate: String,
        title: String
    ) = itemRepository.saveItem(category, cycle, startDate, title)
        .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
        .doOnSuccess { isInitialLoadingProcessor.offer(false) }
        .subscribeOnIO()
}