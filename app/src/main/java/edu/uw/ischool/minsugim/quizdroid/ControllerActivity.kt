package edu.uw.ischool.minsugim.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager

class ControllerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_main)

        var fragmentManager : FragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentTopic()

        val topic: String

        if (savedInstanceState == null) {
            val extras: Bundle = intent.extras
            topic = extras.getString("EXTRA_TEXT")
        } else {
            topic = savedInstanceState.getSerializable("EXTRA_TEXT").toString()
        }

        if (null != fragment) {
            val ft = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("EXTRA_TEXT", topic)
            fragment.arguments = bundle
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.commit()
        }
    }
}