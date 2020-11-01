package com.example.tichandroid.fcm

import androidx.hilt.lifecycle.ViewModelInject
import com.example.tichandroid.base.BaseViewModel
import com.example.tichandroid.data.repository.AuthRepository
import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.Completable
import io.reactivex.Single

class NotificationsViewModel @ViewModelInject constructor(
    schedulerProvider: BaseSchedulerProvider,
    private val authRepository: AuthRepository
) : BaseViewModel(schedulerProvider) {

    fun getFirebaseInstanceToken(): Single<String> = Single.create { emitter ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                emitter.onError(
                    task.exception ?: Exception("Firebase getToken failed")
                )
            } else {
                val token = task.result
                when {
                    token == null -> emitter.onError(NullPointerException("Firebase token is null"))
                    token.isBlank() -> emitter.onError(IllegalArgumentException("Firebase invalid token = $token"))
                    else -> emitter.onSuccess(token)
                }
            }
        }
    }

    fun sendFCMTokenToServer(): Completable = getFirebaseInstanceToken()
        .flatMapCompletable { token -> authRepository.saveDevice(token) }
        .subscribeOnIO()

    companion object {
        private const val TAG = "NotificationsViewModel"
    }
}