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
    @SerialName("deadline_type") val deadlineType: DeadlineType = DeadlineType.Week, // default value is needed for mock data lol
    val category: Category,
    @SerialName("is_important") val isImportant: Boolean,
//    val isReachable: Boolean,
//    val assignedUser: User,
    @SerialName("is_completed") var isComplete: Boolean
) {
    fun isReachable(isFirstWeekCompleted: Boolean) = isFirstWeekCompleted || deadlineType == DeadlineType.Week
}

@Serializable
enum class DeadlineType(val daysNumber: Int) : Comparable<DeadlineType> {
    @SerialName("7")
    Week(7) {
        override fun toString(): String = "Неделя 1"
    },

    @SerialName("30")
    Month(30) {
        override fun toString(): String = "Месяц 1"
    },

    @SerialName("90")
    Full(90) {
        override fun toString(): String = "Месяц 3"
    };
}

@Serializable
data class Progress(val general: Int)

@Serializable
data class UpdateTaskStatus(
    @SerialName("task_id") val taskId: Int,
    @SerialName("is_complete") val isComplete: Boolean
)
