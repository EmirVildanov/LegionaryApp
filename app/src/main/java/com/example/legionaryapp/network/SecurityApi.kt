package com.example.legionaryapp.network

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

class SecurityApi : NetworkService() {
    @KtorExperimentalAPI
    suspend fun login(): HttpResponse {
        return getSuccessfulResponseOrException {
            client.post() {
                url("login_url")
                body = "userLoginInput"
                contentType(ContentType.Application.Json)
            }
        }
    }

    @KtorExperimentalAPI
    suspend fun register(): HttpResponse {
        return getSuccessfulResponseOrException {
            client.post() {
                url("register_url")
                body = "userRegisterInput"
                contentType(ContentType.Application.Json)
            }
        }
    }
}