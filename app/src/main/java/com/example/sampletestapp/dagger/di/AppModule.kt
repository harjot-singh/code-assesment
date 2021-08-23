package com.example.sampletestapp.dagger

import com.example.sampletestapp.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {


    @Provides
    fun providedRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpBuilder.addInterceptor {
            val request = it.request()
            it.proceed(
                request.newBuilder().addHeader("Accept", "application/vnd.github.v3+json").build()
            )
        }

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesTestApi(retrofit: Retrofit): TestApi {
        return retrofit.create(TestApi::class.java)
    }
}