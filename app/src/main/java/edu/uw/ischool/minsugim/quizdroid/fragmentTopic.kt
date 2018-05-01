package edu.uw.ischool.minsugim.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.util.Log

class fragmentTopic : Fragment() {

    private lateinit var quiz : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            quiz = QuizApp.instance.getTopics()[arguments!!.getInt("TopicNumber")]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_topic, container, false)
        val headline: TextView = rootView.findViewById(R.id.textView)
        val description: TextView = rootView.findViewById(R.id.textView2)
        var fragmentManager : FragmentManager = activity!!.supportFragmentManager


        headline.text = quiz!!.title
        description.text = quiz!!.longDesc

        val start: Button = rootView.findViewById(R.id.button)
        start.setOnClickListener {
            val fragment : Fragment = fragmentQuiz()
            val ft = fragmentManager.beginTransaction()
            var bundle = Bundle()
            bundle.putString("Topic", quiz!!.title)
            bundle.putInt("TopicNumber", arguments!!.getInt("TopicNumber"))
            bundle.putInt("Question_Number", 0)
            bundle.putInt("Num_Correct", 0)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }

        return rootView
    }
}