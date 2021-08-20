package com.example.nativeapps.repository.firebase

import androidx.lifecycle.MutableLiveData
import com.example.nativeapps.data.model.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

interface IStorageRepository {
    var firestoreDB: FirebaseFirestore
    var user: FirebaseUser?
    var savedTODOTasks: MutableLiveData<List<Task>>
    var savedDONETasks: MutableLiveData<List<Task>>

    fun initTasks(todo: Boolean): Any
    fun getSavedTasks(): CollectionReference

    fun getTask(task: Task): com.google.android.gms.tasks.Task<DocumentSnapshot>
    fun saveTaskItem(task: Task): com.google.android.gms.tasks.Task<Void>
    fun deleteTask(task: Task): com.google.android.gms.tasks.Task<Void>

    fun setTaskById(string: String, _task: MutableLiveData<Task>, completed: MutableLiveData<Boolean>)
}
