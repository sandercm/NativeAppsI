package com.example.nativeapps.data.model

import java.io.Serializable

data class Task(var taskId: String, var name: String ,var description: String, var completed: Boolean = false): Serializable {
    override fun toString(): String {
        return "${name}\n${description}"
    }
}
