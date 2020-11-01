package com.example.tichandroid.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TichFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, "refreshed token : p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d(TAG, "From : ${p0.from}")

        if (p0.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload : ${p0.data}")
        }

        p0.notification?.let { Log.d(TAG, "Message Notification Body : ${it.body}") }
    }

    companion object {
        private const val TAG = "Tich-FMS"
    }
}