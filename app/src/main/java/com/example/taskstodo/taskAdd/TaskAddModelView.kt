package com.example.taskstodo.taskAdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TaskAddModelView @Inject constructor() : ViewModel() {

    private val _navigateToTaskList = MutableLiveData<String>()

    val navigateToTaskList: LiveData<String>
        get() = _navigateToTaskList

    fun saveTask(task: String) {
        _navigateToTaskList.value = task
    }
}