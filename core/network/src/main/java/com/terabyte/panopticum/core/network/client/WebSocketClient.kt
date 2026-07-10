package com.terabyte.panopticum.core.network.client

import com.terabyte.panopticum.core.model.location.LocationData
import com.terabyte.panopticum.core.model.location.LocationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient {
    private val baseUrl = ""

    private val client = OkHttpClient.Builder().build()

    private var webSocket: WebSocket? = null

    private val _stateFlowLocationState = MutableStateFlow<LocationState>(LocationState.Inactive)
    val stateFlowLocationState = _stateFlowLocationState.asStateFlow()

    fun connect() {
        if (webSocket != null) {
            throw IllegalStateException("WebSocket object is already connected")
        }

        val request = Request.Builder()
            .url(baseUrl)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                _stateFlowLocationState.value = LocationState.Active
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                // to handle incoming messages
                // will be useful later
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                _stateFlowLocationState.value = LocationState.Inactive
                webSocket.close(1000, null)
                this@WebSocketClient.webSocket = null
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                // maybe later I will code to reconnect after failure
            }
        })
    }

    fun sendLocationData(locationData: LocationData) {
        if (webSocket == null || stateFlowLocationState.value != LocationState.Active) {
            throw IllegalStateException("Websocket object is not conencted and cannot send data")
        }
        val json = Json.encodeToString(locationData)
        webSocket?.send(json)
    }

    fun disconnect() {
        webSocket?.close(1000, "Normal closure")
        webSocket = null
        _stateFlowLocationState.value = LocationState.Inactive
    }
}
