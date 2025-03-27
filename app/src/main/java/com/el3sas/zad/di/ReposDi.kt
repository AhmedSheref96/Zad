package com.el3sas.zad.di

import com.el3asas.zad.data.reposImpl.CoursesRepoImpl
import com.el3asas.zad.data.reposImpl.DepartmentsRepoImpl
import com.el3asas.zad.data.reposImpl.TeachersRepoImpl
import com.el3asas.zad.data.reposImpl.YoutubeRepoImpl
import com.el3asas.zad.domain.repos.CoursesRepo
import com.el3asas.zad.domain.repos.DepartmentsRepo
import com.el3asas.zad.domain.repos.TeachersRepo
import com.el3asas.zad.domain.repos.YoutubeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposDi {
    @Binds
    abstract fun provideDepartmentsRepo(coursesRepo: DepartmentsRepoImpl): DepartmentsRepo

    @Binds
    abstract fun provideTeachersRepo(repoImpl: TeachersRepoImpl): TeachersRepo

    @Binds
    abstract fun provideCoursesRepo(repoImpl: CoursesRepoImpl): CoursesRepo

    @Binds
    abstract fun provideYoutubeRepo(repoImpl: YoutubeRepoImpl): YoutubeRepo
}
