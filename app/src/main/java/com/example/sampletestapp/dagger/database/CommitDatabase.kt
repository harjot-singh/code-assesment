package com.example.sampletestapp.dagger.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CommitEntity::class), version = 1)
abstract class CommitDatabase: RoomDatabase() {
    abstract fun commitDao(): CommitDao
}