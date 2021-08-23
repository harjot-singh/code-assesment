package com.example.sampletestapp.dagger

import dagger.Component

@Component(
    modules = [ViewModelModule::class, AppModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}