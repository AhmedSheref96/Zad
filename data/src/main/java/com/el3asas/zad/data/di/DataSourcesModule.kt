package com.el3asas.zad.data.di

import com.el3asas.zad.data.services.YoutubeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class DataSourcesModule {
    @Provides
    fun provideYoutubeDataSource(retrofit: Retrofit): YoutubeService = retrofit.create(YoutubeService::class.java)
}
