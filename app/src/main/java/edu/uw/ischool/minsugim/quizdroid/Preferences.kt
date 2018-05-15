package edu.uw.ischool.minsugim.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView







class Preferences : AppCompatActivity() {

    private var refresh : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        val spinner : Spinner = findViewById(R.id.check_interval)
        val url : EditText = findViewById(R.id.URL)
        val button : Button = findViewById(R.id.save)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.check_intervals, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            Toast.makeText(this, url.text.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
