package com.example.sampletestapp.dagger.di

import com.example.sampletestapp.dagger.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, AppModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}