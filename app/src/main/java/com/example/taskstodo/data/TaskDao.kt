package com.example.taskstodo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * from task_table ORDER BY id ASC")
    fun getAscTasks(): LiveData<List<Task>>

    @Query("SELECT * from task_table LIMIT 1")
    suspend fun getList(): Array<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTask(task: Task)
}