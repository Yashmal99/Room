package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class word(@PrimaryKey @ColumnInfo(name = "Word") val word: String)



