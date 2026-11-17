
package com.diegoalves.stateauthapp.exceptions

import android.util.Log


private const val TAG = "ExceptionsLab"


fun checkWordLength(word: String): String {
    if (word.length > MAXIMUM_WORD_LENGTH) {

        throw WordTooLongException(word)
    }


    return "The word \"$word\" was accepted."
}


fun runCustomExceptionDemo(): List<DemoResult> {
    val results = mutableListOf<DemoResult>()


//    val wordToCheck = "extraordinary"   // 13 characters causes an exception
//     val wordToCheck = "abcdefghij"   // 10 characters, accepted the limit is ‘more than 10’
     val wordToCheck = "short"        // 5 characters accepted

    try {

        val successMessage = checkWordLength(wordToCheck)
        Log.d(TAG, successMessage)
        results.add(DemoResult(message = successMessage, isError = false))
    } catch (exception: WordTooLongException) {

        val errorMessage = "WordTooLongException caught: ${exception.message}"
        Log.e(TAG, errorMessage)
        results.add(DemoResult(message = errorMessage, isError = true))
    }

    return results
}