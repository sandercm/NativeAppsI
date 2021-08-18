package com.example.nativeapps

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.data.viewmodel.PageViewModel
import com.example.nativeapps.repository.firebase.IStorageRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PageViewModelTest : TestCase() {

    private lateinit var viewModel: PageViewModel

    private val mockFireBaseRepository: IStorageRepository = Mockito.mock(IStorageRepository::class.java)

    @Before
    fun setUpMockito() {
        Mockito.`when`(mockFireBaseRepository.savedTODOTasks).thenReturn(MutableLiveData(
            arrayListOf(
                Task("first task", "first desc", false),
                Task("second task", "second desc", false),
                Task("third task", "third desc", false)
            )))
        Mockito.`when`(mockFireBaseRepository.savedDONETasks).thenReturn(MutableLiveData(
            arrayListOf(
                Task("forth task", "forth desc", true),
                Task("fifth task", "fifth desc", true),
                Task("sixth task", "sixth desc", true)
            )))

    }

    @Test
    fun shouldReturnTodos() {
        viewModel = PageViewModel(mockFireBaseRepository)
        val todos = viewModel.getSavedTODOTasks()
        assertTrue(
            todos.value!! == arrayListOf(
                Task("first task", "first desc", false),
                Task("second task", "second desc", false),
                Task("third task", "third desc", false)
            )
        )
    }

    @Test
    fun shouldReturnDone() {
        viewModel = PageViewModel(mockFireBaseRepository)
        val done = viewModel.getSavedDONETasks()
        assertTrue(
            done.value!! == arrayListOf(
                Task("forth task", "forth desc", true),
                Task("fifth task", "fifth desc", true),
                Task("sixth task", "sixth desc", true)
            )
        )
    }
}
