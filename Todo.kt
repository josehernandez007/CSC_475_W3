package com.example.todoapp

import java.util.Date
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")

data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    var title: String,
    var createdAt: Date,
    var completed: Boolean = false
)
