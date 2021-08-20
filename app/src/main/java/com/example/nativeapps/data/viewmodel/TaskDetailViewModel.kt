package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.repository.firebase.IStorageRepository

class TaskDetailViewModel(private val storageRepository: IStorageRepository) : ViewModel() {

    private val _task: MutableLiveData<Task> = MutableLiveData()
    val task: MutableLiveData<Task> = _task

    val completed = MutableLiveData(false)

    fun setTaskById(string: String) {
        println("inside set task")
        println(task)
        println(completed)
        storageRepository.setTaskById(string, task, completed)
    }

    fun setOnCheckedChanged(b: Boolean) {
        task.value?.completed = b
        storageRepository.saveTaskItem(task.value!!)
    }

    fun getImage() {
        storageRepository
    }
}
