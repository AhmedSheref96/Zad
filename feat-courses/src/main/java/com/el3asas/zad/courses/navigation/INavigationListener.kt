package com.el3asas.zad.courses.navigation

import com.el3asas.zad.domain.models.CourseModel

interface INavigationListener {
    fun onCourseCardClicked(courseModel: CourseModel)
}
