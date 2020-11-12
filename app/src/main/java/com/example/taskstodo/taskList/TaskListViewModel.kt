package com.example.taskstodo.taskList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskstodo.data.Task
import com.example.taskstodo.data.TaskRoomDatabase
import com.example.taskstodo.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    application: Application, private var repository: TaskRepository
) : AndroidViewModel(application) {

    val allTasks: LiveData<List<Task>>

    init {
        val tasksDao = TaskRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        repository = TaskRepository(tasksDao)
        allTasks = repository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}