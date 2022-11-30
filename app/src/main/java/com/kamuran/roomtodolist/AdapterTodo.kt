package com.kamuran.roomtodolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView


class AdapterTodo constructor(
    private var todoList: ArrayList<ModelTodo>,
    private val mItemClickListener: ItemClickListener,

    ) : RecyclerView.Adapter<AdapterTodo.TodoViewHolder>() {

    interface ItemClickListener {
        fun deleteItem(position: Int)

        // fun deleteItem(modelTodo: ModelTodo)
        fun updateItem(position: Int)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_row_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoName = holder.itemView.findViewById<TextView>(R.id.todo_tv)
        val rowId = holder.itemView.findViewById<TextView>(R.id.tv_ıd)
        todoName.text = todoList[position].todoName
        rowId.text = (position + 1).toString()

    }


    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class TodoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val deleteBtn = itemView.findViewById<ImageView>(R.id.todo_delete_btn)
        val updateBtn = itemView.findViewById<ImageView>(R.id.todo_edit_btn)

        init {
            deleteBtn.setOnClickListener {

                mItemClickListener.deleteItem(position)
                //id
            }
            updateBtn.setOnClickListener {

                mItemClickListener.updateItem(position)
            }
        }

    }

}