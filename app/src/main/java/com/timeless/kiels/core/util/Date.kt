package com.timeless.kiels.core.util

import java.text.DateFormat
import java.util.Date

object Date {

    fun main() : String {
        val formatter = DateFormat.getDateTimeInstance()
        val currentDate = formatter.format(Date())
        return currentDate.toString()
    }

}