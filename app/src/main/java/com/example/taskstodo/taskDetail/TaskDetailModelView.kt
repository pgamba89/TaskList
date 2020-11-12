package com.example.taskstodo.taskDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskstodo.data.Task
import com.example.taskstodo.data.TaskRoomDatabase
import com.example.taskstodo.domain.TaskRepository

class TaskDetailModelView(id: Long = 0L, application: Application) : ViewModel() {

    private val repository: TaskRepository
    private val task: LiveData<Task>

    init {
        val tasksDao = TaskRoomDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(tasksDao)
        task = repository.getTaskById(id)
    }

    fun getTask() = task

}