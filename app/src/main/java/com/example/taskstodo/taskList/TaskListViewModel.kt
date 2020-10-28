package com.example.taskstodo.taskList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskstodo.data.Word
import com.example.taskstodo.data.WordRoomDatabase
import com.example.taskstodo.domain.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

/*
    private val _navigateToTaskAdd = MutableLiveData<Long>()
    val navigateToTaskAdd
        get() = _navigateToTaskAdd

    fun onTaskAddNavigated() {
        _navigateToTaskAdd.value = null
    }
*/

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}