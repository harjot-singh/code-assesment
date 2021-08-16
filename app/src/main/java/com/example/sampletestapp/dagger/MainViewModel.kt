package com.example.sampletestapp.dagger

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
    val headingText = ObservableField<String>("Please find below the commits")
    val buttonText = ObservableField<String>("Refresh Commits")
    val progressBarVisibilty = ObservableBoolean(false)

    init {
        fetchGitDetails()
    }

    fun fetchGitDetails() {
        disposable = gitApiManager.fetchCommitDetails()
            .subscribeOn(rxSchedulers.getIoScheduler())
            .observeOn(rxSchedulers.getMainScheduler())
            .doOnSubscribe { progressBarVisibilty.set(true) }
            .subscribe({
                progressBarVisibilty.set(false)
                commitListAdapter.setData(it.list)
            }, {
                progressBarVisibilty.set(false)
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.dispose()
    }
}