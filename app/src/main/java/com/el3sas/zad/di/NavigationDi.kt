package com.el3sas.zad.di

import com.el3asas.zad.courses.navigation.INavigationListener
import com.el3sas.zad.navigationListeners.NavigatorListener
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationDi {
    @Binds
    abstract fun provideNavigatorListener(navigatorListener: NavigatorListener): INavigationListener
}
