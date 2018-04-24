package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*

class QuizActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_template)

        val mathQuestions = arrayOf(getString(R.string.math_q1), getString(R.string.math_q2), getString(R.string.math_q3), getString(R.string.math_q4))
        val physicsQuestions = arrayOf(getString(R.string.physics_q1), getString(R.string.physics_q2), getString(R.string.physics_q3), getString(R.string.physics_q4))
        val marvelQuestions = arrayOf(getString(R.string.marvel_q1), getString(R.string.marvel_q2), getString(R.string.marvel_q3), getString(R.string.marvel_q4))

        val mathChoices = arrayOf(getString(R.string.math_c1), getString(R.string.math_c2), getString(R.string.math_c3), getString(R.string.math_c4))
        val physicsChoices = arrayOf(getString(R.string.physics_c1), getString(R.string.physics_c2), getString(R.string.physics_c3), getString(R.string.physics_c4))
        val marvelChoices = arrayOf(getString(R.string.marvel_c1), getString(R.string.marvel_c2), getString(R.string.marvel_c3), getString(R.string.marvel_c4))

        var questionView : TextView = findViewById(R.id.question)
        var questionNum : Int = 0
        var numCorrect : Int = 0

        var topic: String = ""
        if (savedInstanceState == null) {
            val extras: Bundle = intent.extras
            topic = extras.getString("Topic")
            questionNum = extras.getInt("Question_Number")
            numCorrect = extras.getInt("Num_Correct")
        } else {
            topic = savedInstanceState.getSerializable("Topic").toString()
            questionNum = savedInstanceState.getInt("Question_Number")
            numCorrect = savedInstanceState.getInt("Num_Correct")
        }

        val group : RadioGroup = findViewById(R.id.choices)

        fun addButtons (choices: Array<String>) {
            for (choice in choices) {
                val button = RadioButton(this)
                button.text = choice
                group.addView(button)
            }
        }

        when (topic) {
            "Physics" -> {
                addButtons(physicsChoices)
                questionView.text = physicsQuestions[questionNum]
            }
            "Math" -> {
                addButtons(mathChoices)
                questionView.text = mathQuestions[questionNum]

            }
            "Marvel Super Heroes" -> {
                addButtons(marvelChoices)
                questionView.text = marvelQuestions[questionNum]
            }
        }

        val submit : Button = findViewById(R.id.submit)
        submit.setOnClickListener{
                val button: Button = findViewById(group.checkedRadioButtonId)
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("Topic", topic)
                intent.putExtra("Question_Number", questionNum)
                intent.putExtra("Answer", button.text.toString())
                intent.putExtra("Number_Correct", numCorrect)
                startActivity(intent)
        }
    }
}