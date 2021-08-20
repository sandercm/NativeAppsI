package com.example.nativeapps.repository.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FireAuthRepository {
    private lateinit var auth: FirebaseAuth

    fun initFireBaseAuth() {
        auth = Firebase.auth
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
}
