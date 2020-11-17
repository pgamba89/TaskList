package com.example.taskstodo.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database")
                    .allowMainThreadQueries()
                    .addCallback(WordDatabaseCallback())
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class WordDatabaseCallback() : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch{
                    populateDatabase(database.taskDao())
                }
            }
        }

         suspend fun populateDatabase(taskDao: TaskDao) {
            if (taskDao.getList().isEmpty()) {
                var task = Task("Hello")
                taskDao.insert(task)
                task = Task("World!")
                taskDao.insert(task)
            }
        }
    }
}
