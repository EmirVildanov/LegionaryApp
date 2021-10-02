package com.example.legionaryapp.network

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

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
    val deadlineType: DeadlineType,
    val category: Category,
    val isImportant: Boolean,
    val isReachable: Boolean,
    val assignedUser: User,
    val assignedDate: Int,
    var isComplete: Boolean
)
