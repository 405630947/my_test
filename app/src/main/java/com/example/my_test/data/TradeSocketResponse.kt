package com.example.my_test.data
import com.google.gson.annotations.SerializedName
data class TradeSocketResponse(
    @SerializedName("a")
    val a: Int?,
    @SerializedName("e")
    val e: String?,
    @SerializedName("E")
    val bigE: Long?,
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
    @SerializedName("s")
    val s: String?,
    @SerializedName("T")
    val t: Long?
)