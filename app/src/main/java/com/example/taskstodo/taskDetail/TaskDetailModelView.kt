package com.example.taskstodo.taskDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.taskstodo.data.Task
import com.example.taskstodo.domain.TaskRepository

class TaskDetailModelView @ViewModelInject constructor(
    repository: TaskRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val task: LiveData<Task> = repository.getTaskById(savedStateHandle.get<Long>("taskId")!!)

    fun getTask() = task

}