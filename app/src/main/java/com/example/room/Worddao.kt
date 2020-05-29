package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Worddao {

    @Query("SELECT * from word_table ORDER BY Word ASC")
    fun getAlphabetizedWords(): LiveData<List<word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
