package com.example.legionaryapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.legionaryapp.components.tasks.DeadlineTab
import com.example.legionaryapp.network.*
import timber.log.Timber

object UserRepository {
    private var session: UserSession? = null

    val me: MutableState<User?> = mutableStateOf(null)
    val myProgress = mutableStateOf(0)

    val myTasks: MutableState<List<Task>> = mutableStateOf(emptyList())

    suspend fun signIn(id: Int) {
        session = logIn(id)
    }

    suspend fun fetchEverything() {
        me.value = session?.me() ?: notSignedIn()
        myTasks.value = session?.myTasks() ?: notSignedIn()
        fetchProgress()
        Timber.Forest.log(5, "Fetched")
    }

    suspend fun fetchProgress() {
        myProgress.value = session?.myProgress() ?: notSignedIn()
    }

    suspend fun updateTaskStatus(taskId: Int, status: Boolean) {
        myTasks.value = myTasks.value.map { if (it.id == taskId) it.copy(isComplete = status) else it }
        session?.updateTaskStatus(taskId, status) ?: notSignedIn()
        fetchProgress()
    }
}

fun List<Task>.categories() = map { it.category }.distinct()
fun List<Task>.filterByCategory(category: Category) = filter { it.category  == category }
fun List<Task>.filterByDeadlineType(deadlineType: DeadlineType) = filter { it.deadlineType == deadlineType }
fun List<Task>.filterByDeadlineType(deadLineInterval: DeadlineTab) = filterByDeadlineType(deadLineInterval.deadlineType)
fun List<Task>.progress(): Int = filter { it.isComplete }.size * 100 / size
fun List<Task>.sortedByDeadline() = sortedBy { it.deadlineType.daysNumber }


/**
 * First -- more relevant.
 */
fun List<Task>.sortedByRelevance() = sortedByDeadline()

private fun notSignedIn(): Nothing = throw RestException("Not signed in. Call signIn()")
