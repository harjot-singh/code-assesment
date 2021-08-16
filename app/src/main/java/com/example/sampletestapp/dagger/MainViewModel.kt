package com.example.sampletestapp.dagger

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val gitApiManager: GitApiManager,
    val commitListAdapter: CommitListAdapter,
    val rxSchedulers: RxSchedulers
) : ViewModel() {
    lateinit var disposable: Disposable
    val text1 = ObservableField<String>("Please find below the commits")
    val text2 = ObservableField<String>("Refresh Commits")
    val progressBarVisibilty = ObservableBoolean(false)

    init {
        fetchGitDetails()
    }

    fun fetchGitDetails() {
        Log.d("Harjot", "inside fetchGitDetails")
        disposable = gitApiManager.fetchCommitDetails()
            .subscribeOn(rxSchedulers.getIoScheduler())
            .observeOn(rxSchedulers.getMainScheduler())
            .doOnSubscribe { progressBarVisibilty.set(true) }
            .subscribe({
                progressBarVisibilty.set(false)
                Log.d(
                    "Harjot",
                    "inside subscribe" + it.list.toString()
                )
                commitListAdapter.setData(it.list)
            }, {
                progressBarVisibilty.set(false)
                 Log.d("Harjot", "inside error" + it.cause)
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.dispose()
    }
}