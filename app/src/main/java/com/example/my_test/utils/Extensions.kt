package com.example.my_test.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long?.toTimeString(): String {
    if (this == null) {
        return ""
    }
    return try {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.TAIWAN)
        val netDate = Date(this)
        sdf.format(netDate)
    } catch (e: Exception) {
        ""
    }
}

fun String?.keepTwoDigitAfterDot(): String {
    if (this == null) {
        return ""
    }
    val df = DecimalFormat("0.00")
    df.roundingMode = RoundingMode.HALF_UP;
    return try {
        df.format(this.toDouble())
    } catch (e: Exception) {
        ""
    }
}
