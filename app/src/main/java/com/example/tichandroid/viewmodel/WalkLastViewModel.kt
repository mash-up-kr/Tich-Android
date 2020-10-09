package com.example.tichandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.auth.AuthManager
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.AuthRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.processors.BehaviorProcessor

class WalkLastViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val authRepository: AuthRepository,
    private val authManager: AuthManager
) : BaseViewModel(schedulerProvider) {

    private val TAG = "WalkLastViewModel"

    private val isInitialLoadingProcessor = BehaviorProcessor.createDefault(true)

    fun signUp(
        token: String,
        name: String,
        email: String
    ) {
        authRepository.signUp(token, name, email)
            .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
            .doOnSuccess { isInitialLoadingProcessor.offer(false) }
            .doOnSuccess { authManager.saveToken(it.token) }
            .subscribe()
    }

    fun signIn() {
        authRepository.signIn()
            .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
            .doOnSuccess { isInitialLoadingProcessor.offer(false) }
            .doOnSuccess { authManager.saveToken(it.token) }
            .subscribe()
    }
}