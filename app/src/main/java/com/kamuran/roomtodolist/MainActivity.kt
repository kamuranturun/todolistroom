package com.kamuran.roomtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.recycler_row_todo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterTodo.ItemClickListener {

    private lateinit var recyclerview: RecyclerView
    private lateinit var todoList: ArrayList<ModelTodo>
    private lateinit var todoAdapter: AdapterTodo
    private lateinit var todoName: EditText
    private lateinit var tvname: TextView
    private lateinit var btnAddtodo: Button
    private lateinit var todoDatabase: DatabaseTodo
    private var isUpdate: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDatabase = DatabaseTodo.getTodoDatabase(this)!!
        todoName = findViewById(R.id.todo_edittext)
        recyclerview = findViewById(R.id.recyclerview_todo)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        btnAddtodo = findViewById(R.id.add_todo_btn)
        todoList = ArrayList()
        todoAdapter = AdapterTodo(todoList, this)
        recyclerview.adapter = todoAdapter
        btnAddtodo.setOnClickListener(this)
        val list = todoDatabase.getTodoDao().getAllTodo()
        for (i in list) {
            todoList.add(i)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.add_todo_btn -> {
                //add()
                if (isUpdate) {//ekleme
                    Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
                    isUpdate = false
                    add()
                } else {//güncelleme
                    updatedItem()
                }
            }
        }
    }

    private fun updatedItem() {
        val todo = todoName.text.toString()
        val memo = ModelTodo(todo)
        todoDatabase.getTodoDao().addTodo(memo)
        Toast.makeText(this, "${todo}", Toast.LENGTH_SHORT).show()
        btnAddtodo.setText("ekle")
        todoName.text.clear()
        todoAdapter.notifyDataSetChanged()
        isUpdate = true
    }

    private fun add() {
        val todo = todoName.text.toString()
        if (todo != "") {
            todoDatabase.getTodoDao().addTodo(ModelTodo(todo))
            Toast.makeText(this, "added", Toast.LENGTH_LONG).show()
            todoList.clear()
            val list = todoDatabase.getTodoDao().getAllTodo()

            for (i in list) {
                todoList.add(i)
            }
            todoName.text.clear()
            todoAdapter.notifyDataSetChanged()
        }
    }

    override fun deleteItem(position: Int) {
        val item = todoList[position]//o anki eleman
        Toast.makeText(this, "Deleted: $item", Toast.LENGTH_SHORT).show()
        todoList.remove(item)
        todoAdapter.notifyItemChanged(position)
        todoDatabase.getTodoDao().delete(item)
        todoAdapter.notifyItemRemoved(position)
    }

    override fun updateItem(position: Int) {
        isUpdate = false
        val tvtodo = findViewById<TextView>(R.id.todo_tv)
        var tvtodo2 = tvtodo.text
        var todotext = todoName.setText(tvtodo2)
        var todotext2 = todotext.toString()
        btnAddtodo.setText("Güncelle")
        todoAdapter.notifyDataSetChanged()
        Toast.makeText(this, "${position}", Toast.LENGTH_SHORT).show()
    }
}