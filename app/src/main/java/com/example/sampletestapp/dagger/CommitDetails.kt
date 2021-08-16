package com.example.sampletestapp.dagger

import com.google.gson.annotations.SerializedName

data class CommitDetails(
    @SerializedName("sha") val sha: String,
    @SerializedName("commit") val commit: Commit,
)

data class Commit(
    @SerializedName("author") val author: Author,
    @SerializedName("message") val message: String
)

data class Author(
    @SerializedName("name") val name: String
)