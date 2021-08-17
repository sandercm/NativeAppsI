package com.example.nativeapps.data.model

import java.io.Serializable

data class Task(var name: String ,var description: String, var completed: Boolean = false): Serializable {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "name" to name,
            "description" to description,
            "completed" to completed
        )
    }

    override fun toString(): String {
        return "${name}\n${description}"
    }
}
