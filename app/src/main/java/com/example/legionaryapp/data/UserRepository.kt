package com.example.legionaryapp.data

import com.example.legionaryapp.network.*
import timber.log.Timber

object UserRepository {
    private var session: UserSession? = null
    private var fetchedMe: User? = null

    val me: User
        get() = fetchedMe ?: notSignedIn()

    var myTasks: List<Task> = emptyList()
        private set

    suspend fun signIn(id: Int) {
        session = logIn(id)
    }

    fun task(id: Int): Task? = myTasks.find { it.id == id }

    suspend fun fetchEverything() {
        fetchedMe = session?.me() ?: notSignedIn()
        myTasks = session?.myTasks() ?: notSignedIn()
        Timber.Forest.log(5, "Fetched")
    }
}

private fun notSignedIn(): Nothing = throw RestException("Not signed in. Call signIn()")
