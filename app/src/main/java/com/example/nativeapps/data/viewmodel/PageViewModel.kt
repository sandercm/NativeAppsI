package com.example.nativeapps.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.repository.firebase.FireStoreRepository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class PageViewModel : ViewModel() {

    val TAG = "PAGE_VIEW_MODEL"
    var firebaseRepository = FireStoreRepository()
    var savedTODOTasks: MutableLiveData<List<Task>> = MutableLiveData()
    var savedDONETasks: MutableLiveData<List<Task>> = MutableLiveData()

    fun saveTaskToDatabase(task: Task) {
        firebaseRepository.saveAddressItem(task).addOnFailureListener {
            Log.e(TAG, "Failed to save task")
        }
    }

    // get realtime updates from firebase regarding saved tasks
    fun getSavedTODOTasks(): LiveData<List<Task>>{
        firebaseRepository.getSavedAddress().addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedTODOTasks.value = null
                return@EventListener
            }

            var savedTODOList : MutableList<Task> = mutableListOf()
            for (doc in value!!) {
                var task = doc.toObject(Task::class.java)
                if (!task.completed) {
                    savedTODOList.add(task)
                }
            }
            savedTODOTasks.value = savedTODOList
        })

        return savedTODOTasks
    }

    // get realtime updates from firebase regarding saved tasks
    fun getSavedDONETasks(): LiveData<List<Task>>{
        firebaseRepository.getSavedAddress().addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedTODOTasks.value = null
                return@EventListener
            }

            var savedDONEList : MutableList<Task> = mutableListOf()
            for (doc in value!!) {
                var task = doc.toObject(Task::class.java)
                if (task.completed) {
                    savedDONEList.add(task)
                }
            }
            savedDONETasks.value = savedDONEList
        })

        return savedDONETasks
    }

    // delete an address from firebase
    fun deleteTask(task: Task){
        firebaseRepository.deleteAddress(task).addOnFailureListener {
            Log.e(TAG,"Failed to delete task")
        }
    }
}