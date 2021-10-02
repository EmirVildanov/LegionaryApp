package com.example.legionaryapp.network

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String)

@Serializable
data class DeadlineType(
    val id: Int,
    val shortName: String,
    val daysNumber: Int
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val description: String
)

@Serializable
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
