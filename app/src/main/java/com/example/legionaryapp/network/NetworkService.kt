package com.example.legionaryapp.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.websocket.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import io.ktor.util.*
import io.ktor.utils.io.errors.*
import timber.log.Timber
import java.net.UnknownHostException

abstract class NetworkService {

    companion object {
        val jsonFormat = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        const val RESPONSE_CONTENT_READ_LIMIT = 500

        const val BASE_HTTP_URL = "base_http_url"
        const val BASE_WS_URL = "base_ws_url"

        const val AUTHORIZATION_HEADER_NAME = "Authorization"

        const val TICKET_QUERY_PARAM_KEY = "ticket"
        const val ID_QUERY_PARAM_KEY = "id"
        const val USERNAME_QUERY_PARAM_KEY = "username"
    }

    @KtorExperimentalAPI
    val client = HttpClient(OkHttp.create()) {
        install(WebSockets)

        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    suspend fun getSuccessfulResponseOrException(funcBody: suspend () -> HttpResponse): HttpResponse {
        val response: HttpResponse
        try {
            response = funcBody()
            when {
                responseIsSuccessful(response) -> return response
                response.status == HttpStatusCode.Unauthorized -> throw NetworkException("Unauthorized")
                else -> {
                    throw NetworkException(response.content.readUTF8Line(400) ?: "Unknown exception")
                }
            }
        } catch (exception: UnknownHostException) {
            throw NetworkException("Enable to connect ot server")
        } catch (exception: IOException) {
            Timber.i("Something wrong with server connection")
            Timber.i(exception)
            throw NetworkException("Check your Internet connection and try again")
        } catch (e: Exception) {
            throw e
        }
    }

    private fun responseIsSuccessful(httpResponse: HttpResponse): Boolean {
        return httpResponse.status.value in 200..299
    }

    class NetworkException(message: String) : IllegalArgumentException(message)
}