package com.example.my_test.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_test.data.TradeResponse
import com.example.my_test.data.TradeSocketResponse
import com.example.my_test.models.ProfileModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class TradeViewModel : ViewModel() {

    private val client = OkHttpClient()

    private val _tradeData = MutableLiveData<List<TradeResponse>>()
    val tradeData: LiveData<List<TradeResponse>>
        get() = _tradeData

    private val _socketTradeData = MutableLiveData<TradeResponse>()
    val socketTradeData: LiveData<TradeResponse>
        get() = _socketTradeData

    fun getTradeData() = viewModelScope.launch {

        val request = Request.Builder()
            .url("https://api.yshyqxx.com/api/v1/aggTrades?symbol=BTCUSDT")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("DATA", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val resStr = response.body?.string()
                val data: List<TradeResponse> =
                    Gson().fromJson(resStr, Array<TradeResponse>::class.java).toList()
                Log.d("DATA", "response: $data")
                Log.d("DATA SIZE", "list size is ${data.size}")
                _tradeData.postValue(data)
            }
        })
    }

    fun connectWebSocket() = viewModelScope.launch {
        val request = Request.Builder()
            .url("wss://stream.yshyqxx.com/ws/btcusdt@aggTrade")
            .build()
        client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d("WebSocket", "onOpen:${response.body?.string()}")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val data: TradeSocketResponse =
                    Gson().fromJson(text, TradeSocketResponse::class.java)
                val tradeData =
                    TradeResponse(data.a, data.f, data.l, data.m, data.bigM, data.p, data.q, data.t)
                _socketTradeData.postValue(tradeData)
                Log.d("WebSocket", "onMessage:$data")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.d("WebSocket", "onFailure:${response?.body?.string()}")

            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.d("WebSocket", "onClosed: code is $code,reason is $reason")

            }
        })
    }
}