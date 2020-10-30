package com.example.taskstodo.taskDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskDetailModelViewFactory(private val id: Long, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailModelView::class.java)) {
            return TaskDetailModelView(id, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}