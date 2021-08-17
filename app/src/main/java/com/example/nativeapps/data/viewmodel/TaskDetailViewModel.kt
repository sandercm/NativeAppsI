package com.example.nativeapps.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nativeapps.data.model.Task

class TaskDetailViewModel: ViewModel() {

    private val taskData: MutableList<Task> = mutableListOf(
        Task("first", "the first Task", true),
        Task("second", "the second Task", true),
        Task("third", "the Third task", true),
        Task("forth", "the forth Task", true),
        Task("fifth", "the fifth Task", true),
        Task("sixth", "the sixth task", true)
    )

    fun getTaskById(string: String): Task? {
        return taskData.find { it.name == string }
    }

    fun setCompletedStatus(string: String, b: Boolean) {
        taskData.find { it.name == string }?.completed = b
    }
}