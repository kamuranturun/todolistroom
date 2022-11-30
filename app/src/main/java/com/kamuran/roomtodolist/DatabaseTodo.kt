package com.kamuran.roomtodolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ModelTodo::class], version = 1
)
abstract class DatabaseTodo : RoomDatabase() {

    abstract fun getTodoDao(): DaoTodo

    companion object {
        private var instance: DatabaseTodo? = null


        fun getTodoDatabase(context: Context): DatabaseTodo? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    DatabaseTodo::class.java, "todo.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}