package com.example.sampletestapp.dagger.database

import javax.inject.Inject

class CommitRepository @Inject constructor(
    val commitDao: CommitDao
) {
    fun insertCommitEntity(commitEntity: CommitEntity) {
        commitDao.insert(commitEntity)
    }
}
