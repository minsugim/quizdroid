package edu.uw.ischool.minsugim.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager

class ControllerActivity : AppCompatActivity() {

    var currTopic : Topic_One_Desc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_main)

        var fragmentManager : FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentTopic()

        var topic: Int

        val extras: Bundle = intent.extras
        topic = extras.getInt("TopicNumber")

        if (null != fragment) {
            currTopic = QuizApp.instance.getTopics()[0]
            val ft = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("Topic", currTopic!!.title)
            bundle.putInt("TopicNumber", topic)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.commit()
        }
    }
}