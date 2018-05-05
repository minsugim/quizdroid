package edu.uw.ischool.minsugim.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class fragmentQuiz : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView : View = inflater.inflate(R.layout.fragment_quiz, container, false)
        var questionView : TextView = rootView.findViewById(R.id.question)

        var fragmentManager : FragmentManager = activity!!.supportFragmentManager

        var topic = arguments!!.getString("Topic")
        var questionNum = arguments!!.getInt("Question_Number")
        var numCorrect = arguments!!.getInt("Num_Correct")
        val topicNumber = arguments!!.getInt("TopicNumber")

        val group : RadioGroup = rootView.findViewById(R.id.choices)

        fun addButtons (choices: Array<String>) {
            for (choice in choices) {
                val button = RadioButton(context)
                button.text = choice
                group.addView(button)
            }
        }

        val quiz = QuizApp.instance.getTopics()[topicNumber]

        addButtons(quiz.questions[questionNum].answers)
        questionView.text = quiz.questions[questionNum].text


        val submit : Button = rootView.findViewById(R.id.submit)
        submit.setOnClickListener{
            val ft = fragmentManager.beginTransaction()
            ft.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
            val fragment : Fragment = fragmentAnswer()
            val bundle = Bundle()
            var answer = group.checkedRadioButtonId
            bundle.putInt("Question_Number", questionNum)
            bundle.putString("Answer", group.findViewById<RadioButton>(answer).text.toString())
            bundle.putString("Topic", topic)
            bundle.putInt("Num_Correct", numCorrect)
            bundle.putInt("TopicNumber", topicNumber)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.commit()
        }
        return rootView
    }
}