package com.example.sampletestapp.dagger

import android.util.Log
import io.reactivex.Single
import javax.inject.Inject

class GitApiManager @Inject constructor(val apiClient: TestApi) {

    fun fetchCommitDetails(): Single<CommitUiModel> {
        //Log.d("Harjot", "insidefetchCommitDetails" )
        return apiClient.fetchCommitDetails()
            //.doOnError { Log.d("Harjot", "inside doOnError" + it.message) }
            //.doFinally { Log.d("Harjot", "inside doFinally") }
            //.doAfterSuccess { Log.d("Harjot"," commit details" + it.size  ) }
            .map { transformDataToUiModel(it)}
    }

    private fun transformDataToUiModel(list: List<CommitDetails>): CommitUiModel {
        val list = list.flatMap {
            listOf(CommitItemViewModel(it.commit.author.name, it.commit.message, it.sha))
        }
        return CommitUiModel(list)
    }
}

data class CommitUiModel(
    val list: List<CommitItemViewModel>
)