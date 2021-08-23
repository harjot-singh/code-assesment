package com.example.sampletestapp.dagger.di

import android.content.Context
import com.example.sampletestapp.dagger.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, AppModule::class, DatabaseModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}