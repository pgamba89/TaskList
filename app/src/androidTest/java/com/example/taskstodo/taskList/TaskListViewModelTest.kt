package com.example.taskstodo.taskList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.taskstodo.data.Task
import com.example.taskstodo.domain.TaskRepository
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@RunWith(JUnit4::class)
class TaskListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TaskRepository

    @Spy
    private val taskListLiveData: MutableLiveData<List<Task>> = MutableLiveData()

    lateinit var modelView: TaskListViewModel

    private val tasks = listOf(
        Task("Hello"),
        Task("Hi")
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(repository.allTasks).thenReturn(taskListLiveData)
        modelView = TaskListViewModel(repository)
    }

    @Test
    fun getAllTasks() = runBlocking {
        taskListLiveData.value = tasks
        modelView.allTasks.observeForever { }

        assertEquals("Hello", modelView.allTasks.value?.get(0)?.task)
    }
}