package edu.uw.ischool.minsugim.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application(), TopicRepository {

    companion object {
        lateinit var instance : QuizApp
        lateinit var topics : List<Topic>
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        val mathQuestions = arrayOf(getString(R.string.math_q1), getString(R.string.math_q2), getString(R.string.math_q3), getString(R.string.math_q4))
        val physicsQuestions = arrayOf(getString(R.string.physics_q1), getString(R.string.physics_q2), getString(R.string.physics_q3), getString(R.string.physics_q4))
        val marvelQuestions = arrayOf(getString(R.string.marvel_q1), getString(R.string.marvel_q2), getString(R.string.marvel_q3), getString(R.string.marvel_q4))

        val mathChoices = arrayOf(getString(R.string.math_c1), getString(R.string.math_c2), getString(R.string.math_c3), getString(R.string.math_c4))
        val physicsChoices = arrayOf(getString(R.string.physics_c1), getString(R.string.physics_c2), getString(R.string.physics_c3), getString(R.string.physics_c4))
        val marvelChoices = arrayOf(getString(R.string.marvel_c1), getString(R.string.marvel_c2), getString(R.string.marvel_c3), getString(R.string.marvel_c4))

        val mathTopic = Topic("Math","Math is fun and exciting", getString(R.string.math_desc), arrayOf<Quiz>(
                Quiz(mathQuestions[0], mathChoices, 0), Quiz(mathQuestions[1], mathChoices, 1),
                Quiz(mathQuestions[2], mathChoices, 2), Quiz(mathQuestions[3], mathChoices, 3)
        ))

        val physicsTopic = Topic("Physics","Physics can be fun and perhaps exciting", getString(R.string.physics_desc), arrayOf<Quiz>(
                Quiz(physicsQuestions[0], physicsChoices, 0), Quiz(physicsQuestions[1], physicsChoices, 1),
                Quiz(physicsQuestions[2], physicsChoices, 2), Quiz(physicsQuestions[3], physicsChoices, 3)
        ))

        val marvelTopic = Topic("Marvel Super Heroes", "Does Thanos kill everyone?", getString(R.string.marvel_desc), arrayOf<Quiz>(
                Quiz(marvelQuestions[0], marvelChoices, 0), Quiz(marvelQuestions[1], marvelChoices, 1),
                Quiz(marvelQuestions[2], marvelChoices, 2), Quiz(marvelQuestions[3], marvelChoices, 3)
        ))


        topics = listOf(mathTopic, physicsTopic, marvelTopic)

        Log.i("QuizApp", "QuizApp onCreate running")
    }

    override fun getTopics() : List<Topic> {
        return topics
    }
}

data class Quiz(val question : String, val answers : Array<String>, val correctAnswer : Int)
data class Topic(val title : String, val shortDesc : String, val longDesc : String, val questions : Array<Quiz>)

interface TopicRepository {
    fun getTopics() : List<Topic>
}
