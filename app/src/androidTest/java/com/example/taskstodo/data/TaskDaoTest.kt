package com.example.taskstodo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.taskstodo.utils.OneTimeObserver
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var taskDao: TaskDao
    private lateinit var dataBase: TaskRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataBase = Room.inMemoryDatabaseBuilder(context, TaskRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        taskDao = dataBase.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dataBase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertTask_getList() = runBlocking {
        val task = Task("task")
        taskDao.insert(task)

        val allTasks = taskDao.getList()
        assertEquals(allTasks[0], task)
    }

    @Test
    @Throws(Exception::class)
    fun insertTask_getAscTasks() = runBlocking {

        taskDao.getAscTasks().observeOnce {
            assertEquals(0, it.size)
        }

        val task = Task("task")
        taskDao.insert(task)

        taskDao.getAscTasks().observeOnce {
            assertEquals(1, it.size)
        }
    }

    @Test
    fun deleteAll_returnEmptyList() = runBlocking {
        val task = Task("task")
        val task2 = Task("task2")

        taskDao.insert(task)
        taskDao.insert(task2)

        taskDao.deleteAll()
        val allTasks = taskDao.getList()
        assertEquals(true, allTasks.isEmpty())
    }
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

