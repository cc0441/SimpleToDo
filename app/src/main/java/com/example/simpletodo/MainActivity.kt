package com.example.simpletodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter
    var REQUEST_CODE = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val username = getIntent().getStringExtra("username")

        val onLongClickedListener = object : TaskItemAdapter.onLongClickedListener {
            override fun onItemLongClicked(position: Int) {
                //remove the item
                listOfTasks.removeAt(position)
                //notify the adapter
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }

        val onClickedListerner = object : TaskItemAdapter.onClickedListerner {
            override fun onItemClicked(position: Int) {
                val secondActivity = Intent(this@MainActivity, ActivityTwo::class.java)
                startActivity(secondActivity)
            }

        }




//            fun editTaskView() {
//                //edit the item
//                fun onClick(view: View) {
//                    val secondActivity = Intent(this, ActivityTwo::class.java)
//                    secondActivity.putExtra("task", "")
//                    startActivity(secondActivity)
//                }
//
//            }




        //1. Let's detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener {
//            Log.i("Caren", "User clicked on button")
//        }
        loadItems()
        //look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks, onLongClickedListener, onClickedListerner)
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button).setOnClickListener {
            val userInputtedTask = inputTextField.text.toString()
            listOfTasks.add(userInputtedTask)
            //notify the adapter
            adapter.notifyItemInserted(listOfTasks.size - 1)
            inputTextField.setText("")
            saveItems()
        }
    }

//    private fun Intent(onClickedListerner: TaskItemAdapter.onClickedListerner, java: Class<ActivityTwo>): Intent? {
//
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        //Request_code is defined above
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            //extract name value from result extras
//            //val task = data.getExtras().getString("task")
//            //Toast.makeText(this, task, Toast.LENGTH_SHORT).show()
//        }
//    }

    //save the data that user has inputted
    //save data by writing and reading from a file

    //get the file we need
    fun getDataFile() : File {
        //every line is represented a specific task
        return File(filesDir, "data.txt")
    }
    //load the items by reading every line in the data file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
    //save items by writing them into our data file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}