package com.example.taskstodo.taskList


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taskstodo.data.Task
import com.example.taskstodo.data.TaskDao
import com.example.taskstodo.data.observeOnce
import com.example.taskstodo.domain.TaskRepository
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var taskDao: TaskDao

    private lateinit var repository: TaskRepository

    private lateinit var modelView: TaskListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        repository = TaskRepository(taskDao)
        modelView = TaskListViewModel(ApplicationProvider.getApplicationContext())

    }

    @Test
    @Throws(Exception::class)
    fun testGetAllTasks_When_Null() {
        Mockito.`when`(repository.allTasks).thenReturn(null)
        assertNotNull(modelView.allTasks)

    }

    @Test
    @Throws(Exception::class)
    fun testGetAllTasks() = runBlocking {

        modelView.allTasks.observeOnce {
            assertEquals(modelView.allTasks.value, it)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertTask_getAllTasks() = runBlocking {
        val task = Task("task")

        launch(Dispatchers.IO) {
            modelView.insert(task)
        }

        modelView.allTasks.observeOnce {
            assertEquals("task", it[2].task)
        }
    }
}