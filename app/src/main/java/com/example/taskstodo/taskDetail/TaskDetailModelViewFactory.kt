package com.example.taskstodo.taskDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskDetailModelViewFactory(private val id : Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailModelView::class.java)) {
            return TaskDetailModelView(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}