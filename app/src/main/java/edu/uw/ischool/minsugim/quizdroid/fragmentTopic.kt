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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_topic, container, false)
        val headline: TextView = rootView.findViewById(R.id.textView)
        val topic = arguments!!.getString("EXTRA_TEXT")
        val description: TextView = rootView.findViewById(R.id.textView2)
        var fragmentManager : FragmentManager = activity!!.supportFragmentManager

        headline.text = topic

        when (topic) {
            "Physics" ->  {
                description.text = getString(R.string.physics_desc)
                Log.i("fragmentTopic", getString(R.string.physics_desc))
            }
            "Math" -> {
                description.text = getString(R.string.math_desc)
            }
            "Marvel Super Heroes" -> {
                description.text = getString(R.string.marvel_desc)
            }
        }

        val start: Button = rootView.findViewById(R.id.button)
        start.setOnClickListener {
            val fragment : Fragment = fragmentQuiz()
            val ft = fragmentManager.beginTransaction()
            var bundle = Bundle()
            bundle.putString("Topic", topic)
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