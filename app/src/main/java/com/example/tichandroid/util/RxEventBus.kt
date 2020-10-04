package com.example.tichandroid.util

import io.reactivex.subjects.PublishSubject

object RxEventBus {

    val subjectSetDate = PublishSubject.create<String>()
    val subjectSetCycle = PublishSubject.create<String>()

    fun setDate(date: String) {
        subjectSetDate.onNext(date)
    }

    fun setCycle(cycle: String) {
        subjectSetCycle.onNext(cycle)
    }
}