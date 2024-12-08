package com.el3sas.zad.navigationListeners

import com.el3asas.zad.courses.navigation.INavigationListener
import com.el3asas.zad.domain.models.CourseModel
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorListener
    @Inject
    constructor() : INavigationListener {
        override fun onCourseCardClicked(courseModel: CourseModel) {
            Timber.Forest.tag("NavigatorListener").d("onCourseCardClicked")
        }
    }
