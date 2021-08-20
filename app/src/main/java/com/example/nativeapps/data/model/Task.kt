package com.example.nativeapps.data.model

data class Task(var name: String = "", var description: String = "", var completed: Boolean = false) {

    override fun toString(): String {
        return "${name}\n${description}\n$completed"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (name != other.name) return false
        if (description != other.description) return false
        if (completed != other.completed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + completed.hashCode()
        return result
    }
}
