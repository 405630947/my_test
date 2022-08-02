package com.example.my_test.models

import android.util.Log
import okhttp3.*
import java.io.IOException

class GithubModel {

    private val client = OkHttpClient()

    suspend fun callApiData(
        request: Request,
        callBack: (isSuccess: Boolean, result: Response?) -> Unit
    ) {

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("okhttp exception", e.toString())
                callBack.invoke(false, null)
            }

            override fun onResponse(call: Call, response: Response) {
                callBack.invoke(true, response)
            }
        })
    }
}