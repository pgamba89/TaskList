package com.example.taskstodo.domain

import androidx.lifecycle.LiveData
import com.example.taskstodo.data.Word
import com.example.taskstodo.data.WordDao

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}