package com.example.sampletestapp.dagger.manager

import com.example.sampletestapp.dagger.database.CommitEntity
import com.example.sampletestapp.dagger.database.CommitRepository
import com.example.sampletestapp.dagger.views.CommitItemViewModel
import io.reactivex.Single
import javax.inject.Inject

class GitApiManager @Inject constructor(
    val apiClient: TestApi,
    val commitRepository: CommitRepository
) {

    fun fetchCommitDetails(): Single<CommitUiModel> {
        return apiClient.fetchCommitDetails(BRANCH_NAME, MAX_COMMITS)
            .map { transformDataToUiModel(it) }
            .doAfterSuccess {
                it.list.forEach {
                    commitRepository.insertCommitEntity(CommitEntity(it.sha, it.author, it.message))
                }
            }
    }

    private fun transformDataToUiModel(list: List<CommitDetails>): CommitUiModel {
        val list = list.flatMap {
            listOf(CommitItemViewModel(it.commit.author.name, it.commit.message, it.sha, it.commit.author.date))
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