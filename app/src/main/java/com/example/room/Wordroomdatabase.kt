package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(word::class), version = 1, exportSchema = false)
abstract class Wordroomdatabase : RoomDatabase() {

    abstract fun wordDao(): Worddao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val wordDao = database.wordDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word = word("Yash")
                    wordDao.insert(word)
                    word = word("Malasiya")
                    wordDao.insert(word)

                    // TODO: Add your own words!
                    word = word("Evernote")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time
        var INSTANCE: Wordroomdatabase? = null
        fun getDatabase(context: Context,scope: CoroutineScope): Wordroomdatabase {
           return INSTANCE ?: synchronized(this) {
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    Wordroomdatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
               // return instance
                 instance

            }
        }
    }
}