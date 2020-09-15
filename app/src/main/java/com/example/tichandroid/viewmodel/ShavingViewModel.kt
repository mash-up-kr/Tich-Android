package com.example.tichandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.network.ItemSaveModel
import com.example.tichandroid.network.model.ItemSaveRequestDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShavingViewModel(private val model: ItemSaveModel) : BaseViewModel() {

    private val TAG = "MainViewModel"

    private val _itemSaveLiveData = MutableLiveData<ItemSaveRequestDto>()
    val itemSaveLiveData: LiveData<ItemSaveRequestDto>
        get() = _itemSaveLiveData

    fun saveItem(
        accessToken: String,
        category: String,
        cycle: Int,
        startDate: String,
        title: String
    ) {
        addDisposable(
            model.saveItem(accessToken, category, cycle, startDate, title)
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