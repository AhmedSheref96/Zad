package com.el3asas.zad.data.dataSources

import com.el3asas.zad.domain.models.DepartmentModel
import com.el3asas.zad.domain.models.TeacherModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeachersDataSource
    @Inject
    constructor() {
        fun getTeachersForDepartment(departmentModel: DepartmentModel): Flow<List<TeacherModel>> =
            callbackFlow<List<TeacherModel>> {
                val task =
                    Firebase.firestore
                        .collection("Teachers")
                        .whereArrayContains("departments", departmentModel.id)
                        .addSnapshotListener { value, error ->
                            Timber.d("getTeachers: $value")
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (value == null || value.isEmpty) {
                                trySend(emptyList())
                            } else {
                                trySend(value.documents.map(::mapResultToTeacher))
                            }
                        }
                awaitClose {
                    task.remove()
                }
            }

        private fun mapResultToTeacher(result: DocumentSnapshot) =
            TeacherModel(
                id = result.id,
                name = result.getString("name") ?: "",
                imageUrl = result.getString("imageUrl") ?: "",
            ).apply {
                Timber.d("mapResultToTeacher: $this")
            }
    }
