package com.example.sampletestapp.dagger

import io.reactivex.Single
import javax.inject.Inject

class GitApiManager @Inject constructor(val apiClient: TestApi) {

    fun fetchCommitDetails(): Single<CommitUiModel> {
        return apiClient.fetchCommitDetails(BRANCH_NAME, MAX_COMMITS)
            .map { transformDataToUiModel(it) }
    }

    private fun transformDataToUiModel(list: List<CommitDetails>): CommitUiModel {
        val list = list.flatMap {
            listOf(CommitItemViewModel(it.commit.author.name, it.commit.message, it.sha))
        }
        return CommitUiModel(list)
    }

    companion object {
        const val BRANCH_NAME = "main"
        const val MAX_COMMITS = "50"
    }
}

data class CommitUiModel(
    val list: List<CommitItemViewModel>
)