package com.example.sampletestapp.dagger.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CommitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commitEntity: CommitEntity)
}