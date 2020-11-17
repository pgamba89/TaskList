package com.example.taskstodo.domain

import androidx.lifecycle.LiveData
import com.example.taskstodo.data.Task
import com.example.taskstodo.data.TaskDao
import com.example.taskstodo.utils.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAscTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getTaskById(id: Long): LiveData<Task> {
        return taskDao.getTaskWithId(id)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }
}