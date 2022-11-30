package com.kamuran.roomtodolist


import android.graphics.ColorSpace.Model
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoTodo {
    @Insert
    fun addTodo(todo: ModelTodo)

    @Query("SELECT * FROM todo_table ORDER BY todo_id DESC")
    fun getAllTodo(): List<ModelTodo>

    @Query("UPDATE todo_table SET todo_name=:todoname")
    fun update(todoname: String)

//  @Query("DELETE FROM todo_table WHERE todo_id =:id")
    // fun delete(id: Int)

    // @Query("DELETE FROM todo_table WHERE todo_id =:id")
    @Delete
    fun delete(todo: ModelTodo)


}