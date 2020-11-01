package com.example.tichandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.auth.AuthManager
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.AuthRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor

class WalkLastViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val authRepository: AuthRepository,
    private val authManager: AuthManager
) : BaseViewModel(schedulerProvider) {

    companion object {
        private val TAG = "WalkLastViewModel"
    }

    private val isInitialLoadingProcessor = BehaviorProcessor.createDefault(true)

    fun signUpButtonClick(token: String, name: String, email: String): Single<String> {
        return if (authManager.getToken().isNullOrEmpty()) {
            authRepository.signUp(token, name, email)
                .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
                .doOnSuccess { isInitialLoadingProcessor.offer(false) }
                .doOnSuccess { authManager.saveToken(it.token) }
                .map { it.name }
                .subscribeOnIO()
        } else {
            authRepository.signIn()
                .doOnSubscribe { isInitialLoadingProcessor.offer(true) }
                .doOnSuccess { isInitialLoadingProcessor.offer(false) }
                .doOnSuccess { authManager.saveToken(it.token) }
                .map { it.name }
                .subscribeOnIO()
        }
    }
}