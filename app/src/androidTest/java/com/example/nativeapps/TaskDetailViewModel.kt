package com.example.nativeapps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.data.viewmodel.TaskDetailViewModel
import com.example.nativeapps.repository.firebase.IStorageRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.junit.Rule




@RunWith(AndroidJUnit4::class)
class TaskDetailViewModelTest : TestCase() {

    private lateinit var viewModel: TaskDetailViewModel

    private val mockFireBaseRepository: IStorageRepository = Mockito.mock(IStorageRepository::class.java)

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpMockito() {
        viewModel = TaskDetailViewModel(mockFireBaseRepository)
        Mockito.`when`(mockFireBaseRepository.setTaskById("first task", viewModel.task, viewModel.completed)).then {
            val task = Task("first task", "first desc", true)
            viewModel.task.value = task
            viewModel.completed.value = task.completed
            return@then Unit
        }
    }

    @Test
    fun testIfTaskGetsSet() {
        assertTrue(viewModel.task.value == null)
        assertTrue(viewModel.completed.value == false)

        viewModel.setTaskById("first task")

        assertTrue(viewModel.task.value == Task("first task", "first desc", true))
        assertTrue(viewModel.completed.value == true)
    }
}