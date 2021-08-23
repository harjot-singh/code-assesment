package com.example.sampletestapp.dagger.manager

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulers @Inject constructor() {

    fun getIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    fun getMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}