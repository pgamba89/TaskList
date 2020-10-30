package com.example.taskstodo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(@ColumnInfo(name = "task") val task: String){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
