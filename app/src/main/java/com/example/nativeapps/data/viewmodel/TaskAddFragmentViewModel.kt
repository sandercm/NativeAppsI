package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nativeapps.repository.firebase.StorageRepository
import com.google.android.gms.tasks.Task
import com.example.nativeapps.data.model.Task as ModelTask

class TaskAddViewModel: ViewModel() {
    private var firebaseRepository = StorageRepository()

    fun storeTask(task: ModelTask): Task<Void> {
        return firebaseRepository.saveTaskItem(task)
    }
}