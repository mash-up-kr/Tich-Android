package com.example.tichandroid.view.ui.showcycle

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.ItemRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import com.mashup.android.base.extension.getTodayDateByFormat
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class ShowCycleViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    itemRepository: ItemRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel(schedulerProvider) {

    private val isEmptyProcessor = BehaviorProcessor.createDefault(false)
    private val isInitialLoadingProcessor = BehaviorProcessor.createDefault(true)

    val isEmpty: Flowable<Boolean> = isEmptyProcessor
    val isInitialLoading: Flowable<Boolean> = isInitialLoadingProcessor

    val items = itemRepository.fetchItems()
        .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
        .doOnSuccess { isInitialLoadingProcessor.offer(false) }
        .doOnSuccess { isEmptyProcessor.offer(it.isEmpty()) }
        .map {
            val items = it.toShowCycleAdapterItems()
            val soonItems = items.filter { item -> item.dueDate <= 14 }
            soonItems.sortedBy { item -> item.dueDate }
            val firstItem = soonItems.first()
            val headerText = when {
                soonItems.first().dueDate == 0 -> {
                    context.getString(
                        R.string.show_cycle_today_changed,
                        context.getString(soonItems.first().categoryNameKey)
                    )
                }
                soonItems.isNotEmpty() -> {
                    context.getString(R.string.show_cycle_soon_changed)
                }
                else -> context.getString(R.string.show_cycle_no_changed)
            }
            val bannerItem = if (firstItem.dueDate == 0) {
                ShowCycleAdapterItem.Banner(firstItem.categoryId.toBannerResourceId())
            } else {
                null
            }
            listOfNotNull(
                ShowCycleAdapterItem.Header(getTodayDateByFormat("mm-dd"), headerText),
                bannerItem,
                ShowCycleAdapterItem.ItemHeader(items.size)
            ) + items
        }
        .subscribeOnIO()

    private fun Int.toBannerResourceId(): Int = when (this) {
        1 -> R.drawable.card_shaver
        2 -> R.drawable.card_toothbrush
        3 -> R.drawable.card_towel
        4 -> R.drawable.card_dishcloth
        else -> R.drawable.card_lens
    }
}