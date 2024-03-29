package com.example.sampletestapp.dagger.views

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.androidresources.ResourceManager
import com.example.sampletestapp.R
import com.example.sampletestapp.dagger.manager.GitApiManager
import com.example.sampletestapp.dagger.manager.RxSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val gitApiManager: GitApiManager,
    val commitListAdapter: CommitListAdapter,
    val rxSchedulers: RxSchedulers,
    val resourceManager: ResourceManager
) : ViewModel(), LifecycleObserver {
    lateinit var disposable: Disposable
    val headingText = ObservableField<String>()
    val buttonText = ObservableField<String>("Refresh Commits")
    val progressBarVisibilty = ObservableBoolean(false)

    init {
        fetchGitDetails()
        headingText.set(resourceManager.getString(R.string.commit_header))
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