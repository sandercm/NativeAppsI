package com.example.nativeapps.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.repository.firebase.IStorageRepository

class PageViewModel(private val firebaseRepository: IStorageRepository) : ViewModel() {

    private val TAG = "PAGE_VIEW_MODEL"

    init {
        firebaseRepository.initTasks(true)
        firebaseRepository.initTasks(false)
    }

    // These functions return a non Mutable version of the lists
    fun getSavedTODOTasks(): LiveData<List<Task>> {
        return firebaseRepository.savedTODOTasks
    }

    fun getSavedDONETasks(): LiveData<List<Task>> {
        return firebaseRepository.savedDONETasks
    }

    // delete an address from firebase
    fun deleteTask(task: Task) {
        firebaseRepository.deleteTask(task).addOnFailureListener {
            Log.e(TAG, "Failed to delete task")
        }
    }
}
