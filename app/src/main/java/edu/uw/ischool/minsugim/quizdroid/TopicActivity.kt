package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_main)


        val headline: TextView = findViewById(R.id.textView)
        headline.text = ""
        if (savedInstanceState == null) {
            val extras: Bundle = intent.extras
            if(extras != null) {
                headline.text = extras.getString("EXTRA_TEXT")
            }
        } else {
            headline.text = savedInstanceState.getSerializable("EXTRA_TEXT") as String
        }
    }
}