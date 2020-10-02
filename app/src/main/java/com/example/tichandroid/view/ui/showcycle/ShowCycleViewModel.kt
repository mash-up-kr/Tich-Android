package com.example.tichandroid.view.ui.showcycle

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.ItemRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import com.example.tichandroid.view.ui.showcycle.toShowCycleAdapterItems
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class ShowCycleViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    itemRepository: ItemRepository
) : BaseViewModel(schedulerProvider) {

    private val isEmptyProcessor = BehaviorProcessor.createDefault(false)
    private val isInitialLoadingProcessor = BehaviorProcessor.createDefault(true)

    val isEmpty: Flowable<Boolean> = isEmptyProcessor
    val isInitialLoading: Flowable<Boolean> = isInitialLoadingProcessor

    val items = itemRepository.fetchItems()
        .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
        .doOnSuccess { isInitialLoadingProcessor.offer(false) }
        .doOnSuccess { isEmptyProcessor.offer(it.isEmpty()) }
        .map { it.toShowCycleAdapterItems() }
        .subscribeOnIO()
}