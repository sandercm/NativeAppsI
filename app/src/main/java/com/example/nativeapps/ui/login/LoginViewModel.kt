package com.example.nativeapps.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.R
import com.example.nativeapps.repository.firebase.FireAuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel(private val loginRepository: FireAuthRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val loginResult = MutableLiveData<LoginResult>()

    fun initFireAuth() {
        loginRepository.initFireBaseAuth()
    }

    fun login(username: String, password: String): Task<AuthResult> {
        // launches a separate asynchronous job
        return loginRepository.signInWithEmailAndPassword(username, password)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
