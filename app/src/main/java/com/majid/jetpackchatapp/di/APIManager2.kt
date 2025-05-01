package com.majid.jetpackchatapp.di

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

class APIManager(val client: HttpClient) {

    suspend inline fun <reified TResponse> GET(endpoint: String): Result<TResponse> {
        return try {
            val response: TResponse = client.get(endpoint).body()
            Result.success(response)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend inline fun <reified TRequest, reified TResponse> POST(
        endpoint: String,
        requestBody: TRequest
    ): Result<TResponse> {
        return try {
            val response: TResponse = client.post(endpoint) {
                setBody(requestBody)
                contentType(ContentType.Application.Json)
            }.body()
            Result.success(response)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend inline fun <reified TRequest, reified TResponse> PUT(
        endpoint: String,
        requestBody: TRequest
    ): Result<TResponse> {
        return try {
            val response: TResponse = client.put(endpoint) {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body()
            Result.success(response)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend inline fun <reified TResponse> DELETE(
        endpoint: String
    ): Result<TResponse> {
        return try {
            val response: TResponse = client.delete(endpoint).body()
            Result.success(response)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}

@Serializable
sealed class APIResult<T> {
    @Serializable
    data class Success<T>(val data: T) : APIResult<T>()

    @Serializable
    data class Error(
        val message: String,
        val code: Int? = null
    ) : APIResult<Nothing>()
}
