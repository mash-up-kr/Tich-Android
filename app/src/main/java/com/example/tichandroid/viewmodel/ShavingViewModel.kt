package com.example.tichandroid.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.model.Item
import com.example.tichandroid.data.repository.ItemRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShavingViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val itemRepository: ItemRepository
) : BaseViewModel(schedulerProvider) {

    private val TAG = "ShavingViewModel"

    private val _itemSaveLiveData = MutableLiveData<Item>()

    val itemSaveLiveData: LiveData<Item>
        get() = _itemSaveLiveData

    fun saveItem(
        category: String,
        cycle: Int,
        startDate: String,
        title: String
    ) {
        addDisposable(
            itemRepository.saveItem(category, cycle, startDate, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        _itemSaveLiveData.postValue(this)
                        Log.v(TAG, "category : $category")
                    }
                }, {
                    Log.v(TAG, "save item error, msg : ${it.message}")
                })
        )
    }
}