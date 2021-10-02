package com.example.legionaryapp.network

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GarbageRestResponse<D>(val data: D)

@Serializable
@Immutable
data class User(val id: Int, val name: String)

@Serializable
@Immutable
data class DeadlineType(
    val id: Int,
    val shortName: String,
    val daysNumber: Int
)

@Serializable
@Immutable
data class Category(
    val id: Int,
    val name: String,
    val description: String
)

@Serializable
@Immutable
data class Task(
    val id: Int,
    val title: String,
    val description: String,
//    val deadlineType: DeadlineType,
    val category: Category,
    @SerialName("is_important") val isImportant: Boolean,
//    val isReachable: Boolean,
//    val assignedUser: User,
    @SerialName("is_completed") var isComplete: Boolean
)

@Serializable
data class Progress(val general: Int)
