package com.example.sampletestapp.dagger.di

import android.app.Application

class App: Application() {
    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }
}