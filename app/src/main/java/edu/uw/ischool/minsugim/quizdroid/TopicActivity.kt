package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic_main)

        val headline: TextView = findViewById(R.id.textView)
        val description: TextView = findViewById(R.id.textView2)
        headline.text = ""
        if (savedInstanceState == null) {
            val extras: Bundle = intent.extras
            headline.text = extras.getString("EXTRA_TEXT")
        } else {
            headline.text = savedInstanceState.getSerializable("EXTRA_TEXT").toString()
        }

        when (headline.text) {
            "Physics" -> description.text = getString(R.string.physics_desc)
            "Math" -> description.text = getString(R.string.math_desc)
            "Marvel Super Heroes" -> description.text = getString(R.string.marvel_desc)
        }

        val start: Button = findViewById(R.id.button)
        start.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("Topic", headline.text)
            intent.putExtra("Question_Number", 0)
            intent.putExtra("Num_Correct", 0)
            startActivity(intent)
        }
    }
}