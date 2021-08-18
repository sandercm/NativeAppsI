package com.example.nativeapps.data.viewmodel

import android.widget.CompoundButton
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.repository.firebase.IStorageRepository
import com.example.nativeapps.repository.firebase.StorageRepository

class TaskDetailViewModel(private val firebaseRepository: IStorageRepository): ViewModel() {

    private val _task: MutableLiveData<Task> = MutableLiveData()
    val task: MutableLiveData<Task> = _task

    val completed = MutableLiveData(false)

    fun setTaskById(string: String) {
        firebaseRepository.getSavedTasks().document(string).get().addOnSuccessListener {
                document ->
            val task = document.toObject(Task::class.java)!!
            _task.value = task
            completed.value = task.completed
        }.addOnFailureListener { e -> println(e) }
    }

    fun setOnCheckedChanged(button: CompoundButton, b: Boolean) {
        task.value?.completed = b
        firebaseRepository.saveTaskItem(task.value!!)
    }
}