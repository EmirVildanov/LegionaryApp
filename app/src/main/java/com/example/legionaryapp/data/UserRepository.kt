package com.example.legionaryapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.legionaryapp.network.*
import timber.log.Timber

object UserRepository {
    private var session: UserSession? = null
    private var fetchedMe: User? = null

    val me: MutableState<User?> = mutableStateOf(null)

    val myTasks: MutableState<List<Task>> = mutableStateOf(emptyList())

    suspend fun signIn(id: Int) {
        session = logIn(id)
    }

    @Deprecated("NO")
    fun task(id: Int): Task? = myTasks.component1().find { it.id == id }

    suspend fun fetchEverything() {
        me.value = session?.me() ?: notSignedIn()
        myTasks.value = session?.myTasks() ?: notSignedIn()
        Timber.Forest.log(5, "Fetched")
    }
}

private fun notSignedIn(): Nothing = throw RestException("Not signed in. Call signIn()")
