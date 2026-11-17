package com.diegoalves.stateauthapp.exceptions

import android.util.Log


private const val TAG = "ExceptionsLab"

class Light(private val turnedOn: Boolean) {


    fun isLightOn(): Boolean {
        return turnedOn
    }
}

fun useLight(light: Light): String {


    check(light.isLightOn()) { "The light is not turned on." }


    return "The light is on, it is safe to use it."
}


fun registerNumber(number: Int): String {
    require(number >= 0) { "The number must not be negative, received: $number." }


    return "Number $number accepted."
}

fun runPreconditionDemo(): List<DemoResult> {
    val results = mutableListOf<DemoResult>()


//    val light = Light(turnedOn = false)
    val light = Light(turnedOn = true)


    try {

        val successMessage = useLight(light)
        Log.d(TAG, successMessage)
        results.add(DemoResult(message = successMessage, isError = false))
    } catch (exception: IllegalStateException) {

        val errorMessage = "IllegalStateException caught: ${exception.message}"
        Log.e(TAG, errorMessage)
        results.add(DemoResult(message = errorMessage, isError = true))
    }


//    val userNumber = -5
    val userNumber = 7


    try {

        val successMessage = registerNumber(userNumber)
        Log.d(TAG, successMessage)
        results.add(DemoResult(message = successMessage, isError = false))
    } catch (exception: IllegalArgumentException) {

        val errorMessage = "IllegalArgumentException caught: ${exception.message}"
        Log.e(TAG, errorMessage)
        results.add(DemoResult(message = errorMessage, isError = true))
    }

    return results
}