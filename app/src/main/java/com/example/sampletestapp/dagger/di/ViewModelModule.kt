package com.example.sampletestapp.dagger.di

import androidx.lifecycle.ViewModelProvider
import com.example.sampletestapp.dagger.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}