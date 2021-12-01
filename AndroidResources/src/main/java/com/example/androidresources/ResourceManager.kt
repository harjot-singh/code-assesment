package com.example.androidresources

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceManager @Inject constructor(val context: Context) {

    fun getResources(): Resources {
        return context.resources
    }

    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

}