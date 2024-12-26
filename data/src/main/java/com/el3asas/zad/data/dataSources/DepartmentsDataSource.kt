package com.el3asas.zad.data.dataSources

import com.el3asas.zad.domain.models.DepartmentModel
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
class DepartmentsDataSource
    @Inject
    constructor() {
        fun getDepartments(): Flow<List<DepartmentModel>> =
            callbackFlow<List<DepartmentModel>> {
                val task =
                    Firebase.firestore
                        .collection("Departments")
                        .addSnapshotListener { value, error ->
                            Timber.d("getDepartments: $value")
                            if (error != null) {
                                close(error)
                                return@addSnapshotListener
                            }
                            if (value == null || value.isEmpty) {
                                trySend(emptyList())
                            } else {
                                trySend(value.documents.map(::mapResultToDepartment))
                            }
                        }
                awaitClose {
                    task.remove()
                }
            }

        private fun mapResultToDepartment(result: DocumentSnapshot) =
            DepartmentModel(
                id = result.id,
                title = result.getString("title") ?: "",
                description = result.getString("description") ?: "",
                imageUrl = result.getString("imageUrl") ?: "",
            ).apply {
                Timber.d("mapResultToCourses: $this")
            }
    }
