package com.example.nativeapps.repository.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nativeapps.data.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.android.gms.tasks.Task as AsyncTask

class StorageRepository: IStorageRepository {

    val TAG = "FIREBASE_REPOSITORY"
    override var firestoreDB = FirebaseFirestore.getInstance()
    override var user = FirebaseAuth.getInstance().currentUser
    override var savedTODOTasks: MutableLiveData<List<Task>> = MutableLiveData()
    override var savedDONETasks: MutableLiveData<List<Task>> = MutableLiveData()

    private fun filterTasks(
        e: FirebaseFirestoreException?,
        value: QuerySnapshot?,
        list: MutableLiveData<List<Task>>,
        status: Boolean
    ) {
        if (e != null) {
            Log.w(TAG, "Listen failed.", e)
            list.value = null
            return
        }

        val savedList: MutableList<Task> = mutableListOf()
        for (doc in value!!) {
            val task = doc.toObject(Task::class.java)
            if (task.completed == status) {
                savedList.add(task)
            }
        }
        list.value = savedList
    }

    override fun initTasks(todo: Boolean): ListenerRegistration {
        return getSavedTasks().addSnapshotListener { value, e ->
            filterTasks(e, value, if (todo) savedDONETasks else savedTODOTasks, todo)
        }
    }

    override fun saveTaskItem(task: Task): AsyncTask<Void> {
        println("saving task")
        println(task)
        return firestoreDB.collection("users").document(user!!.email.toString())
            .collection("saved_tasks").document(task.name).set(task)
    }

    override fun getSavedTasks(): CollectionReference {
        return firestoreDB.collection("users/${user!!.email.toString()}/saved_tasks")
    }

    override fun getTask(task: Task): com.google.android.gms.tasks.Task<DocumentSnapshot> {
        return firestoreDB.collection("users/${user!!.email.toString()}/saved_tasks")
            .document(task.name).get()
    }

    override fun deleteTask(task: Task): AsyncTask<Void> {
        return firestoreDB.collection("users/${user!!.email.toString()}/saved_tasks")
            .document(task.name).delete()
    }
}