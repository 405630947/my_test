package com.example.my_test.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_test.data.Item
import com.example.my_test.data.ProfileDetailResponse
import com.example.my_test.data.UserListResponse
import com.example.my_test.models.GithubModel
import com.example.my_test.models.ProfileModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.Request

class GithubViewModel : ViewModel() {

    private val model = GithubModel()

    private val _userList = MutableLiveData<List<UserListResponse>>()
    val userList: LiveData<List<UserListResponse>>
        get() = _userList

    fun getUserList() = viewModelScope.launch {
        val request = Request.Builder()
            .url("https://api.github.com/users")
            .build()
        model.getUserList(request) { isSuccess, result ->
            if (isSuccess) {
                result?.let {
                    val resStr = result.body?.string()
                    val data: List<UserListResponse> =
                        Gson().fromJson(resStr, Array<UserListResponse>::class.java).toList()
                    Log.d("DATA", "response: $data")
                    _userList.postValue(data)
                }

            }
        }
    }
}