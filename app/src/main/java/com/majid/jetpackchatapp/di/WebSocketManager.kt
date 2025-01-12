package com.majid.jetpackchatapp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WebSocketManager(private val client: HttpClient) {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    suspend fun connectToChannel(sender: String, receiver: String) {
        client.webSocket(
            method = HttpMethod.Get,
            host = "http://localhost:8080/api/v1",
            path = "/ws/$sender/$receiver"
        ) {
            launch {
                for (message in incoming) {
                    if (message is Frame.Text) {
                        _message.emit(message.readText())
                    }
                }
            }
        }
    }

    suspend fun sendMessage(sender: String, receiver: String, message: String) {
        client.webSocket(
            method = HttpMethod.Get,
            host = "http://localhost:8080/api/v1",
            path = "/ws/$sender/$receiver"
        ) {
            send(Frame.Text(message))
        }
    }
}