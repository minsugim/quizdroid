package edu.uw.ischool.minsugim.quizdroid

import android.app.Activity
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONArray
import java.io.*
import java.net.URL
import android.content.Context.MODE_PRIVATE
import android.R.id.edit
import android.content.SharedPreferences
import android.os.Environment
import org.json.JSONObject
import java.nio.channels.FileChannel
import java.nio.charset.Charset


class QuizApp : android.app.Application(), TopicRepository {

    companion object {
        lateinit var instance : QuizApp
        lateinit var topics : ArrayList<Topic_One_Desc>
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

//        val mathQuestions = arrayOf(getString(R.string.math_q1), getString(R.string.math_q2), getString(R.string.math_q3), getString(R.string.math_q4))
//        val physicsQuestions = arrayOf(getString(R.string.physics_q1), getString(R.string.physics_q2), getString(R.string.physics_q3), getString(R.string.physics_q4))
//        val marvelQuestions = arrayOf(getString(R.string.marvel_q1), getString(R.string.marvel_q2), getString(R.string.marvel_q3), getString(R.string.marvel_q4))
//
//        val mathChoices = arrayOf(getString(R.string.math_c1), getString(R.string.math_c2), getString(R.string.math_c3), getString(R.string.math_c4))
//        val physicsChoices = arrayOf(getString(R.string.physics_c1), getString(R.string.physics_c2), getString(R.string.physics_c3), getString(R.string.physics_c4))
//        val marvelChoices = arrayOf(getString(R.string.marvel_c1), getString(R.string.marvel_c2), getString(R.string.marvel_c3), getString(R.string.marvel_c4))
//
//        val mathTopic = Topic("Math","Math is fun and exciting", getString(R.string.math_desc), arrayOf<Quiz>(
//                Quiz(mathQuestions[0], 0, mathChoices), Quiz(mathQuestions[1], 1, mathChoices),
//                Quiz(mathQuestions[2], 2, mathChoices), Quiz(mathQuestions[3], 3, mathChoices)
//        ))

//        val physicsTopic = Topic("Physics","Physics can be fun and perhaps exciting", getString(R.string.physics_desc), arrayOf<Quiz>(
//                Quiz(physicsQuestions[0], physicsChoices, 0), Quiz(physicsQuestions[1], physicsChoices, 1),
//                Quiz(physicsQuestions[2], physicsChoices, 2), Quiz(physicsQuestions[3], physicsChoices, 3)
//        ))
//
//        val marvelTopic = Topic("Marvel Super Heroes", "Does Thanos kill everyone?", getString(R.string.marvel_desc), arrayOf<Quiz>(
//                Quiz(marvelQuestions[0], marvelChoices, 0), Quiz(marvelQuestions[1], marvelChoices, 1),
//                Quiz(marvelQuestions[2], marvelChoices, 2), Quiz(marvelQuestions[3], marvelChoices, 3)
//        ))

        var questions = listOf<Topic>()
        val url = "http://tednewardsandbox.site44.com/questions.json"
        val files = Environment.getExternalStorageDirectory()
        val file = File(files, "questions.json")
        val stream = FileInputStream(file)
        var jsonStr : String = ""

        try {
            val editor = applicationContext.getSharedPreferences("questions.json", MODE_PRIVATE).edit()
            val fc = stream.channel
            val bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
            jsonStr = Charset.defaultCharset().decode(bb).toString()
            editor.putString(jsonStr, jsonStr)
            editor.commit()
//            val response = URL(url).readText()
//            Log.i("quiz", response)
//            val gson = Gson()
//            val topic = gson.fromJson<Topic_One_Desc>(response, Topic_One_Desc::class.java)

        } catch (e : Exception) {
            Log.e("QuizApp", "fail")
        } finally {
            stream.close()
        }

        val allTopics = arrayListOf<Topic_One_Desc>()
        val jsonArray : JSONArray = JSONArray(jsonStr)
        for (subject in 0 until jsonArray.length()) {
            val topic =  jsonArray.getJSONObject(subject)
            
            val questions = topic.getJSONArray("questions")
            val quizzes = arrayListOf<Quiz>()
            for (questionNum in 0 until questions.length()) {
                val question = questions.getJSONObject(questionNum)
                val jsonAns = question.getJSONArray("answers")
                val answers = Array<String>(jsonAns.length()) { "answers" }
                for (choice in 0 until jsonAns.length()) {
                    answers[choice] = jsonAns[choice].toString()
                }
                quizzes.add(Quiz(question.getString("text"), question.getInt("answer"), answers))
            }
            allTopics.add(Topic_One_Desc(topic.getString("title"), topic.getString("desc"), quizzes))
         }
        topics = allTopics
        Log.i("QuizApp", "QuizApp onCreate running")
    }

    override fun getTopics() : ArrayList<Topic_One_Desc> {
        return topics
    }
}

data class Quiz(val text : String, val answer : Int, val answers : Array<String>)
data class Topic(val title : String, val shortDesc : String, val longDesc : String, val questions : Array<Quiz>)
data class Topic_One_Desc(val title : String, val desc : String, val questions : ArrayList<Quiz>)

interface TopicRepository {
    fun getTopics() : ArrayList<Topic_One_Desc>
}
