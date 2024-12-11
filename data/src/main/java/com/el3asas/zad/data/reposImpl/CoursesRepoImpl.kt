package com.el3asas.zad.data.reposImpl

import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.repos.CoursesRepo
import javax.inject.Inject
import kotlin.random.Random

class CoursesRepoImpl
@Inject
constructor() : CoursesRepo {
    override suspend fun getCourses(): List<CourseModel> = listOf(
        CourseModel(
            id = Random.nextInt(),
            title = "course1",
            description = "course 1 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course2",
            description = "course 2 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course3",
            description = "course 3 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course4",
            description = "course 4 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course5",
            description = "course 5 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course6",
            description = "course 6 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ), CourseModel(
            id = Random.nextInt(),
            title = "course2",
            description = "course 2 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course3",
            description = "course 3 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course4",
            description = "course 4 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course5",
            description = "course 5 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course6",
            description = "course 6 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ), CourseModel(
            id = Random.nextInt(),
            title = "course2",
            description = "course 2 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course3",
            description = "course 3 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course4",
            description = "course 4 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course5",
            description = "course 5 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
        CourseModel(
            id = Random.nextInt(),
            title = "course6",
            description = "course 6 description",
            imageUrl="https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
            teachers = listOf()
        ),
    )
}
