package com.majid.jetpackchatapp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.sse.SSE
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val ktorModule = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
                url {
                    host = "localhost"
                    port = 8080
                    protocol = URLProtocol.HTTP
                    encodedPath = "/api/v1/user"
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(WebSockets)
            install(SSE)
        }
    }
    single { APIManager(get()) }
    factory { WebSocketManager(get()) }
}