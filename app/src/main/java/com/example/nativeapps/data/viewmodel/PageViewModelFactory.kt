package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nativeapps.repository.firebase.IStorageRepository

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
