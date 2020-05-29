package com.example.room

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: Worddao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: word) {
        wordDao.insert(word)
    }
}
