package com.example.sampletestapp.dagger.di

import android.content.Context
import androidx.room.Room
import com.example.androidresources.AndroidResourcesModule
import com.example.sampletestapp.dagger.database.CommitDao
import com.example.sampletestapp.dagger.database.CommitDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AndroidResourcesModule::class])
class DatabaseModule(val context: Context) {

    @Singleton
    @Provides
    fun providesCommitDatabase(): CommitDatabase {
        return Room.databaseBuilder(context, CommitDatabase::class.java, "COMMIT_DETAILS_DATABASE.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesCommitDao(commitDatabase: CommitDatabase): CommitDao {
        return commitDatabase.commitDao()
    }

    @Singleton
    @Provides
    fun providesContext(): Context {
        return context
    }
}