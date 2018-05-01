package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var subjects : MutableList<String> = mutableListOf()
        for(topic in QuizApp.instance.getTopics()) {
            subjects.add(topic.title)
        }
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, subjects)
        listView.adapter = adapter
        listView.setOnItemClickListener({ _, _, position, _ ->
            val intent = Intent(this, ControllerActivity::class.java)
            intent.putExtra("EXTRA_TEXT", listView.getItemAtPosition(position).toString())
            intent.putExtra("TopicNumber", position)
            startActivity(intent)
        })
    }
}
