
package com.diegoalves.stateauthapp.exceptions


const val MAXIMUM_WORD_LENGTH = 10



open class WordTooLongException(val word: String) : Exception() {

    override val message: String
        get() = "The word \"$word\" has ${word.length} characters, the maximum is $MAXIMUM_WORD_LENGTH."
}