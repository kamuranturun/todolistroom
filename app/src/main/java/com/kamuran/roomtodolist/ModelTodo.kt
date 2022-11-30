package com.kamuran.roomtodolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "todo_table")
data class ModelTodo(
    @ColumnInfo(name = "todo_name")
    var todoName:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    var todoId:Int=0
}
