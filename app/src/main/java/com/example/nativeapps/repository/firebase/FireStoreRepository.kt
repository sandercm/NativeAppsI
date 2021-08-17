package com.example.nativeapps.repository.firebase

import com.example.nativeapps.data.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.Task as AsyncTask

class FireStoreRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    // save address to firebase
    fun saveAddressItem(task: Task): AsyncTask<Void> {
        return firestoreDB.collection("users").document(user!!.email.toString())
            .collection("saved_tasks").document(task.name).set(task)
    }

    // get saved addresses from firebase
    fun getSavedAddress(): CollectionReference {
        return firestoreDB.collection("users/${user!!.email.toString()}/saved_tasks")
    }

    fun deleteAddress(task: Task): AsyncTask<Void> {
        return firestoreDB.collection("users/${user!!.email.toString()}/saved_tasks")
            .document(task.name).delete()
    }
}