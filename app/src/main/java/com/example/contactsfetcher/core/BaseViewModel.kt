package com.example.contactsfetcher.core

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }

    protected fun Disposable.unsubscribeOnDestroy() {
        disposables.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}