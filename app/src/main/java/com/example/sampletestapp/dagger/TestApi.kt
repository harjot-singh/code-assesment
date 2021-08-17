package com.example.sampletestapp.dagger

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {
    @GET("repos/harjot-singh/code-assesment/commits")
    fun fetchCommitDetails(
        @Query("sha") branchName: String,
        @Query("per_page") maxCommits: String
    ): Single<List<CommitDetails>>
}