package com.stochanskyi.librariesdemo.presentaiton.utils.livedata

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T> {

    companion object {
        private const val TAG = "SingleLiveEvent"
    }

    private val mPending = AtomicBoolean(false)

    constructor() : super()

    constructor(@Nullable value: T?) : super() {
        setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        if (hasActiveObservers()) {
            Timber.tag(TAG)
                .w("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner) {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}