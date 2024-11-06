
package com.example.todoapp

import java.time.Instant
import java.util.Date

object TodoManager {

    private val todoList = mutableListOf<Todo>()

    fun getAllTodos(): List<Todo> {
        return todoList
    }

    fun addTodo(title: String) {
        todoList.add(Todo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now()), completed = false))
    }

    fun deleteTodo(id: Int) {
        todoList.removeIf { it.id == id }
    }

    fun toggleTodoCompletion(id: Int) {
        todoList.find { it.id == id }?.let {
            it.completed = !it.completed
        }
    }
}
