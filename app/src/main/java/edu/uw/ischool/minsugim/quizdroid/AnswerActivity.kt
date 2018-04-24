package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.util.Log
import android.widget.Button

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer_activity)

        val mathAnswer = arrayOf(getString(R.string.math_a1), getString(R.string.math_a2), getString(R.string.math_a3), getString(R.string.math_a4))
        val physicsAnswer = arrayOf(getString(R.string.physics_a1), getString(R.string.physics_a2), getString(R.string.physics_a3), getString(R.string.physics_a4))
        val marvelAnswer = arrayOf(getString(R.string.marvel_a1), getString(R.string.marvel_a2), getString(R.string.marvel_a3), getString(R.string.marvel_a4))

        var answer : String = ""
        var correctAnswer : String = ""
        var score : Int
        var topic : String = ""
        var questionNum : Int = 0

        if (savedInstanceState == null) {
            val extras: Bundle = intent.extras
            answer = extras.getString("Answer")
            topic = extras.getString("Topic")
            questionNum = extras.getInt("Question_Number")
            score = extras.getInt("Number_Correct")
        } else {
            answer = savedInstanceState.getSerializable("Answer").toString()
            topic = savedInstanceState.getSerializable("Topic").toString()
            questionNum = savedInstanceState.getInt("Question_Number")
            score = savedInstanceState.getInt("Number_Correct")
        }

        when (topic) {
            "Physics" -> {
                correctAnswer = physicsAnswer[questionNum]
            }

            "Math" -> {
                correctAnswer = mathAnswer[questionNum]
            }

            "Marvel Super Heroes" -> {
                correctAnswer = marvelAnswer[questionNum]
            }
        }

        val currScore : TextView = findViewById(R.id.score)

        if (answer ==  correctAnswer) {
            score++
        }
        findViewById<TextView>(R.id.given_answer).text = "Your answer was: " + answer
        findViewById<TextView>(R.id.correct_answer).text = "The correct answer is: " + correctAnswer
        currScore.text = "You have " + score.toString() + " out of 4 correct"

        val nextButton : Button = findViewById(R.id.next)
        if (questionNum == 3) {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            nextButton.text = "Next"
            nextButton.setOnClickListener {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("Topic", topic)
                intent.putExtra("Question_Number", questionNum + 1)
                intent.putExtra("Num_Correct", score)
                startActivity(intent)
            }
        }
    }

}