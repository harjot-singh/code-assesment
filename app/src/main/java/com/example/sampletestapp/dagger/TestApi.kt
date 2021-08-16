package com.example.sampletestapp.dagger

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface TestApi {
    //@GET("repos/harjot-singh/DataStructures/commits")
    @GET("repos/octocat/hello-world/commits")
    fun fetchCommitDetails(): Single<List<CommitDetails>>
}