package com.example.my_test.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_test.data.Item
import com.example.my_test.data.ProfileDetailResponse
import com.example.my_test.data.UserDetailResponse
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

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail: LiveData<UserDetailResponse>
        get() = _userDetail

    fun getUserList() = viewModelScope.launch {
        val request = Request.Builder()
            .url("https://api.github.com/users")
            .build()
        model.callApiData(request) { isSuccess, result ->
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

    fun getUserDetail(userName: String) = viewModelScope.launch {
        val request = Request.Builder()
            .url("https://api.github.com/users/$userName")
            .build()
        model.callApiData(request) { isSuccess, result ->
            if (isSuccess) {
                result?.let {
                    val resStr = result.body?.string()
                    val data: UserDetailResponse =
                        Gson().fromJson(resStr, UserDetailResponse::class.java)
                    Log.d("DATA", "response: $data")
                    _userDetail.postValue(data)
                }
            }
        }
    }

    fun getFollowerList(userName: String, mode: String) = viewModelScope.launch{
        val url =
            if (mode == "0") "https://api.github.com/users/$userName/followers"
            else "https://api.github.com/users/$userName/following"
        val request = Request.Builder()
            .url(url)
            .build()
        model.callApiData(request) { isSuccess, result ->
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