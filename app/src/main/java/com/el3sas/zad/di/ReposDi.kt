package com.el3sas.zad.di

import com.el3asas.zad.data.reposImpl.CoursesRepoImpl
import com.el3asas.zad.domain.repos.CoursesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposDi {
    @Binds
    abstract fun provideCoursesRepo(coursesRepo: CoursesRepoImpl): CoursesRepo
}
