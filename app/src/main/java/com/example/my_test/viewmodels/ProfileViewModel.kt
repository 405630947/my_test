package com.example.my_test.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_test.data.Item
import com.example.my_test.data.ProfileDetailResponse
import com.example.my_test.data.TradeResponse
import com.example.my_test.models.ProfileModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.Request

class ProfileViewModel : ViewModel() {

    private val model = ProfileModel()

    private val _profileList = MutableLiveData<List<Item>>()
    val profileList: LiveData<List<Item>>
        get() = _profileList

    fun getProfileList() = viewModelScope.launch {
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/winwiniosapp/interview/main/interview.json")
            .build()
        model.getProfileList(request) { isSuccess, result ->
            if (isSuccess) {
                result?.let {
                    val resStr = result.body?.string()
                    val data: ProfileDetailResponse =
                        Gson().fromJson(resStr, ProfileDetailResponse::class.java)
                    Log.d("DATA", "response: $data")
                    _profileList.postValue(data.data.items)
                }

            }
        }
    }
}
