package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nativeapps.repository.firebase.FireAuthRepository
import com.example.nativeapps.repository.firebase.IStorageRepository
import com.example.nativeapps.repository.firebase.StorageRepository
import com.example.nativeapps.ui.login.LoginViewModel

class PageViewModelFactory(private var storageRepository: IStorageRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(
                storageRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}