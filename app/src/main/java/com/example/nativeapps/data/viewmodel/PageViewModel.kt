package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task

class PageViewModel : ViewModel() {

    val todoList: LiveData<MutableList<Task>> = MutableLiveData(
        arrayListOf(
            Task("first", "the first Task"),
            Task("second", "the second Task"),
            Task("third", "the Third task")
        ))

    val doneList: LiveData<MutableList<Task>> = MutableLiveData(
        arrayListOf(
            Task("forth", "the first Task"),
            Task("fifth", "the second Task"),
            Task("sixth", "the Third task")
        ))
}