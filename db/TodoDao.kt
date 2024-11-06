package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("Delete FROM todo_table where id = :id")
    fun deleteTodo(id : Int)
}