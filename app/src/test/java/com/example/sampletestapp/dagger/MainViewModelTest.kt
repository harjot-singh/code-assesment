package com.example.sampletestapp.dagger

import com.example.sampletestapp.dagger.manager.CommitUiModel
import com.example.sampletestapp.dagger.manager.GitApiManager
import com.example.sampletestapp.dagger.manager.RxSchedulers
import com.example.sampletestapp.dagger.views.CommitItemViewModel
import com.example.sampletestapp.dagger.views.CommitListAdapter
import com.example.sampletestapp.dagger.views.MainViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.internal.schedulers.IoScheduler
import org.junit.Assert
import org.junit.Test

class MainViewModelTest {
    val gitApiManager: GitApiManager = mockk()
    val commitListAdapter: CommitListAdapter = mockk()
    val rxSchedulers: RxSchedulers = mockk()
    lateinit var subject: MainViewModel
    val slot = slot<List<CommitItemViewModel>>()

    @Test
    fun fetchGitDetails_apiRetunrsSuccess_AdapterIsSet() {
        val commitUiModel: CommitUiModel = mockk()
        val commitItemViewModel: CommitItemViewModel = mockk()
        every { commitUiModel.list } returns listOf(commitItemViewModel)
        every { rxSchedulers.getIoScheduler() } returns IoScheduler()
        every { rxSchedulers.getMainScheduler() } returns IoScheduler()
        every { gitApiManager.fetchCommitDetails() } returns Single.just(commitUiModel)

        subject = MainViewModel(gitApiManager, commitListAdapter, rxSchedulers)

        verify { commitListAdapter.setData(capture(slot)) }
        Assert.assertEquals(1, slot.captured.size)
        Assert.assertEquals(false, subject.progressBarVisibilty.get())
    }

    @Test
    fun fetchGitDetails_apiReturnsError_AdapterIsSet() {
        val error: Throwable = mockk()
        every { rxSchedulers.getIoScheduler() } returns IoScheduler()
        every { rxSchedulers.getMainScheduler() } returns IoScheduler()
        every { gitApiManager.fetchCommitDetails() } returns Single.error(error)

        subject = MainViewModel(gitApiManager, commitListAdapter, rxSchedulers)

        verify(inverse = true) { commitListAdapter.setData(capture(slot)) }
        Assert.assertEquals(false, subject.progressBarVisibilty.get())
    }

}