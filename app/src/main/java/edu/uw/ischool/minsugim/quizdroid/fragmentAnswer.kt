package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button

class fragmentAnswer : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView : View = inflater.inflate(R.layout.fragment_answer, container, false)
        var fragmentManager : FragmentManager = activity!!.supportFragmentManager

        var questionNum = arguments!!.getInt("Question_Number")
        var score = arguments!!.getInt("Num_Correct")

        val topicNumber = arguments!!. getInt("TopicNumber")
        val quiz = QuizApp().getTopics()[topicNumber].questions[questionNum]
        val answer = arguments!!.getString("Answer")

        var correctAnswer : Int = quiz.answer - 1
//
        val currScore : TextView = rootView.findViewById(R.id.score)
//
        if (answer == quiz.answers[correctAnswer]) {
            score++
        }

        val actualAnswer = getString(R.string.correct_answer) + quiz.answers[correctAnswer]
        val yourScore = getString(R.string.score_1) + score + " out of " + QuizApp().getTopics()[topicNumber].questions.size + " correct"

        rootView.findViewById<TextView>(R.id.given_answer).text = getString(R.string.your_answer) + answer
        rootView.findViewById<TextView>(R.id.correct_answer).text =  actualAnswer
        currScore.text =  yourScore

        val nextButton : Button = rootView.findViewById(R.id.next)
        if (questionNum >= QuizApp().getTopics()[topicNumber].questions.size - 1) {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            nextButton.text = "Next"
            nextButton.setOnClickListener {
                val fragment : Fragment = fragmentQuiz()
                val ft = fragmentManager.beginTransaction()
                ft.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.fade_in, android.R.anim.fade_out)
                var bundle = Bundle()
                bundle.putInt("Question_Number", questionNum + 1)
                bundle.putInt("Num_Correct", score)
                bundle.putInt("TopicNumber", topicNumber)
                fragment.arguments = bundle
                ft.replace(R.id.fragment_placeholder, fragment)
                ft.addToBackStack(null)
                ft.commit()
            }
        }
        return rootView
    }

}