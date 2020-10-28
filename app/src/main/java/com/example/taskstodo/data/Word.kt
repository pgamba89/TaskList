package com.example.taskstodo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "word_table")
data class Word(@ColumnInfo(name = "word") val word: String){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
