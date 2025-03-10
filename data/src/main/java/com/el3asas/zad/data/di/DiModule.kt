package com.el3asas.zad.data.di

import com.el3asas.zad.domain.Constants
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit
            .Builder()
            .baseUrl(Constants.YOUTUBE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)

    @Singleton
    @Provides
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit = retrofitBuilder.build()

    @Singleton
    @Provides
    fun providesOkhttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                LoggingInterceptor
                    .Builder()
                    .setLevel(Level.BASIC)
                    .addQueryParam("key", Constants.YOUTUBE_API_KEY)
                    .build(),
            ).callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
}
