package com.timeless.kiels

import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormat
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(DateFormat.getDateTimeInstance().format(Date()))
        assertEquals(4, 2 + 2)
    }
}