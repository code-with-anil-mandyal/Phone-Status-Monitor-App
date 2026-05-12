package com.codewithmandyal.phonestatusmonitorapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedTime(): String {

    val formatter = SimpleDateFormat(
        "hh:mm aa",
        Locale.ENGLISH
    )

    return formatter.format(Date(this))
}