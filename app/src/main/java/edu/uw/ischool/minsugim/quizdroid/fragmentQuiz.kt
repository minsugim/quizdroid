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
        val mathQuestions = arrayOf(getString(R.string.math_q1), getString(R.string.math_q2), getString(R.string.math_q3), getString(R.string.math_q4))
        val physicsQuestions = arrayOf(getString(R.string.physics_q1), getString(R.string.physics_q2), getString(R.string.physics_q3), getString(R.string.physics_q4))
        val marvelQuestions = arrayOf(getString(R.string.marvel_q1), getString(R.string.marvel_q2), getString(R.string.marvel_q3), getString(R.string.marvel_q4))

        val mathChoices = arrayOf(getString(R.string.math_c1), getString(R.string.math_c2), getString(R.string.math_c3), getString(R.string.math_c4))
        val physicsChoices = arrayOf(getString(R.string.physics_c1), getString(R.string.physics_c2), getString(R.string.physics_c3), getString(R.string.physics_c4))
        val marvelChoices = arrayOf(getString(R.string.marvel_c1), getString(R.string.marvel_c2), getString(R.string.marvel_c3), getString(R.string.marvel_c4))

        val rootView : View = inflater.inflate(R.layout.fragment_quiz, container, false)
        var questionView : TextView = rootView.findViewById(R.id.question)

        var fragmentManager : FragmentManager = activity!!.supportFragmentManager

        var topic = arguments!!.getString("Topic")
        var questionNum = arguments!!.getInt("Question_Number")
        var numCorrect = arguments!!.getInt("Num_Correct")

        val group : RadioGroup = rootView.findViewById(R.id.choices)

        fun addButtons (choices: Array<String>) {
            for (choice in choices) {
                val button = RadioButton(context)
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

        val submit : Button = rootView.findViewById(R.id.submit)
        submit.setOnClickListener{
            val button: Button = rootView.findViewById(group.checkedRadioButtonId)
            val answer = button.text.toString()
            Log.i("fragmentQuiz", answer)
            val ft = fragmentManager.beginTransaction()
            ft.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
            val fragment : Fragment = fragmentAnswer()
            val bundle = Bundle()
            bundle.putString("Topic", topic)
            bundle.putInt("Question_Number", questionNum)
            bundle.putString("Answer", answer)
            bundle.putInt("Num_Correct", numCorrect)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.commit()
        }
        return rootView
    }
}