package com.example.sampletestapp.dagger.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommitEntity(
    @PrimaryKey val commitHash: String,
    @ColumnInfo val committer: String,
    @ColumnInfo val commitMessage: String
)