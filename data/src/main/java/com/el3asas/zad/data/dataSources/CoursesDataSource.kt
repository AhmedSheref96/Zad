package com.el3asas.zad.data.dataSources

import com.el3asas.zad.domain.models.CourseModel
import com.el3asas.zad.domain.models.PropertiesModel
import com.el3asas.zad.domain.models.TeacherModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesDataSource
    @Inject
    constructor() {
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        fun getCourses(teacherId: String): Flow<List<CourseModel>> =
            callbackFlow<List<CourseModel>> {
                val task =
                    Firebase.firestore
                        .let { firebase ->
                            firebase
                                .collection("Courses")
                                .whereArrayContains("teachers", teacherId)
                                .addSnapshotListener { value, error ->
                                    Timber.d("getCourses: $value")
                                    if (error != null) {
                                        close(error)
                                        return@addSnapshotListener
                                    }
                                    if (value == null || value.isEmpty) {
                                        trySend(emptyList())
                                    } else {
                                        val courses = value.documents.map(::mapResultToCourse)

                                        coroutineScope.launch {
                                            val updatedCourses =
                                                courses.map { course ->
                                                    val updatedTeachers =
                                                        course.teachers
                                                            .map { teacher ->
                                                                async {
                                                                    val teacherDocument =
                                                                        firebase
                                                                            .collection("Teachers")
                                                                            .document(teacher.id)
                                                                            .get()
                                                                            .await()
                                                                    teacher.mapResultToTeacher(teacherDocument)
                                                                }
                                                            }.awaitAll()

                                                    course.copy(teachers = updatedTeachers)
                                                }
                                            trySend(updatedCourses)
                                        }
                                    }
                                }
                        }
                awaitClose {
                    task.remove()
                }
            }

        private fun mapResultToCourse(result: DocumentSnapshot): CourseModel {
            // Safely get the properties as a List of Maps
            val propertiesList = result.get("properties") as? List<*>
            val properties =
                propertiesList?.mapNotNull { property ->
                    if (property is Map<*, *>) {
                        PropertiesModel(
                            iconUrl = property["iconUrl"].toString(),
                            title = property["title"].toString(),
                            value = property["value"].toString(),
                        )
                    } else {
                        null
                    }
                } ?: emptyList()

            val teachers =
                (result.get("teachers") as? List<*>)
                    ?.mapNotNull { any ->
                        if (any is String) {
                            TeacherModel(id = any)
                        } else {
                            null
                        }
                    }?.filterNotNull() ?: emptyList()

            return CourseModel(
                id = result.id,
                title = result.getString("title") ?: "",
                description = result.getString("description") ?: "",
                imageUrl = result.getString("imageUrl") ?: "",
                teachers = teachers, // Assuming you handle teachers separately
//                department = emptyList(), // Assuming you handle department separately
                properties = properties,
                courseYoutubeUrl = result.getString("courseYoutubeUrl") ?: "",
            ).apply {
                Timber.d("mapResultToCourse: $this")
            }
        }

        private fun TeacherModel.mapResultToTeacher(result: DocumentSnapshot) =
            this
                .copy(
                    id = result.id,
                    name = result.getString("name") ?: "",
                    imageUrl = result.getString("imageUrl") ?: "",
                ).apply {
                    Timber.d("mapResultToTeacher: $this")
                }
    }
