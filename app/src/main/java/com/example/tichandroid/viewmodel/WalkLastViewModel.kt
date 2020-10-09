package com.example.tichandroid.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.model.UserInfo
import com.example.tichandroid.data.repository.AuthRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WalkLastViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val authRepository: AuthRepository
) : BaseViewModel(schedulerProvider) {

    private val TAG = "WalkLastViewModel"

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo>
        get() = _userInfoLiveData

    fun signUp(
        token: String,
        name: String,
        email: String
    ) {
        addDisposable(
            authRepository.signUp(token, name, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        _userInfoLiveData.postValue(this)
                    }
                }, {
                    Log.v(TAG, "save item error, msg : ${it.message}")
                })
        )
    }
}