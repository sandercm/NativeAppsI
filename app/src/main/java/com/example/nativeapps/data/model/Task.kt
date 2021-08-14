package com.example.nativeapps.data.model

data class Task(var name: String ,var description: String, var completed: Boolean = false) {
    override fun toString(): String {
        return "${name}\n${description}"
    }
}
