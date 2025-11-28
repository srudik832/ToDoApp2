package com.example.todoapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    private var nextId = 0

    val tasks = mutableStateListOf<ToDoItem>()

    fun addTask(text: String) {
        val clean = text.trim()
        if (clean.isNotEmpty()) {
            tasks.add(ToDoItem(id = nextId++, text = clean))
        }
    }

    fun removeTaskById(id: Int) {
        val idx = tasks.indexOfFirst { it.id == id }
        if (idx != -1) tasks.removeAt(idx)
    }

    fun clearAll() {
        tasks.clear()
    }
}
