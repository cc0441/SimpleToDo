package com.example.simpletodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ActivityTwo : AppCompatActivity()  {

    var listOfTasks = mutableListOf<String>()
    val inputTextEditField = findViewById<EditText>(R.id.editTask)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        fun finishEdit(v: View) {
//        val editTask = findViewById<EditText>(R.id.editTask)
//        val data = Intent()
//        data.putExtra("task", editTask.getText().toString())
//        setResult(RESULT_OK, data)
            findViewById<Button>(R.id.button2).setOnClickListener {
                val inputtedTask = inputTextEditField.text.toString()
                // listOfTasks.add(userInputtedTask)
                //notify the adapter
                // adapter.notifyItemInserted(listOfTasks.size - 1)
                // inputTextField.setText("")
                // saveItems()

            }
            finish()
        }



    }




}