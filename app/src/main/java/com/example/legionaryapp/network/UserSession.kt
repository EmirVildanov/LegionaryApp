package com.example.legionaryapp.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.Exception

private val client = HttpClient(OkHttp.create()) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

data class UserSession(val id: Int) {
    suspend fun <T> makeRequest(request: suspend UserSession.() -> T) = wrapException { request() }
}

suspend fun logIn(id: Int): UserSession {
    val session = UserSession(id)
    session.me()
    return session
}

fun HttpRequestBuilder.includeAuth(session: UserSession) {
    header("myId", session.id.toString())
}

suspend fun UserSession.me(): User = makeRequest {
    client.get<User>(ME_ENDPOINT) {
        includeAuth(this@me)
    }
}

suspend fun UserSession.myTasks(): List<Task> = makeRequest {
    client.get<List<Task>>(MY_TASKS_ENDPOINT) {
        includeAuth(this@myTasks)
    }
}

suspend fun UserSession.updateTaskStatus(taskId: Int, newIsComplete: Boolean) = makeRequest {
    client.get<List<Task>>(MY_TASKS_ENDPOINT) {
        includeAuth(this@updateTaskStatus)
        contentType(ContentType.Application.Json)
    }
}

private suspend fun <T> wrapException(f: suspend () -> T): T = try {
    f()
} catch (e: Exception) {
    throw RestException(e)
}

class RestException(e: Exception) : Exception(e) {
    constructor(msg: String) : this(Exception(msg))
}
