package com.example.my_test.data
import com.google.gson.annotations.SerializedName


data class TradeResponse(
    @SerializedName("a")
    val a: Int?,
    @SerializedName("f")
    val f: Int?,
    @SerializedName("l")
    val l: Int?,
    @SerializedName("m")
    val m: Boolean?,
    @SerializedName("M")
    val bigM: Boolean?,
    @SerializedName("p")
    val p: String?,
    @SerializedName("q")
    val q: String?,
    @SerializedName("T")
    val t: Long?
)