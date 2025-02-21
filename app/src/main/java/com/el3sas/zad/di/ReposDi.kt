package com.el3sas.zad.di

import com.el3asas.zad.data.reposImpl.DepartmentsRepoImpl
import com.el3asas.zad.data.reposImpl.TeachersRepoImpl
import com.el3asas.zad.domain.repos.DepartmentsRepo
import com.el3asas.zad.domain.repos.TeachersRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposDi {
    @Binds
    abstract fun provideDepartmentsRepo(coursesRepo: DepartmentsRepoImpl): DepartmentsRepo

    @Binds
    abstract fun provideTeachersRepo(repoImpl: TeachersRepoImpl): TeachersRepo
}
