package com.example.my_test.data

import com.google.gson.annotations.SerializedName


data class ProfileDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: ProfileDetail,
    @SerializedName("message")
    val message: String
)

data class ProfileDetail(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class Item(
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("nickName")
    val nickName: String
)