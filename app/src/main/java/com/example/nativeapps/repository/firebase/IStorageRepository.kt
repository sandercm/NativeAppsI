package com.example.nativeapps.repository.firebase

import androidx.lifecycle.MutableLiveData
import com.example.nativeapps.data.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

interface IStorageRepository {
    var firestoreDB: FirebaseFirestore
    var user: FirebaseUser?
    var savedTODOTasks: MutableLiveData<List<Task>>
    var savedDONETasks: MutableLiveData<List<Task>>

    fun initTasks(todo: Boolean): Any
    fun saveTaskItem(task: Task): com.google.android.gms.tasks.Task<Void>
    fun getSavedTasks(): CollectionReference
    fun getTask(task: Task): com.google.android.gms.tasks.Task<DocumentSnapshot>
    fun deleteTask(task: Task): com.google.android.gms.tasks.Task<Void>
}