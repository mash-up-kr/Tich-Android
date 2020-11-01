package com.example.tichandroid.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BaseViewModelActivity : AppCompatActivity() {

    private val disposables by lazy { CompositeDisposable() }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected fun Disposable.addToDisposables(): Disposable = addTo(disposables)
}