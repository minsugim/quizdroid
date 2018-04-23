package edu.uw.ischool.minsugim.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TabHost
import android.widget.TabHost.OnTabChangeListener
import edu.uw.ischool.minsugim.quizdroid.R.id.tab1
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var subject: String = "Math"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val host = findViewById<TabHost>(R.id.tabHost)
        host.setup()

        // Tab 1
        var spec: TabHost.TabSpec = host.newTabSpec("Math")
        spec.setContent(R.id.tab1)
        spec.setIndicator("Math")
        host.addTab(spec)


        //Tab 2
        spec = host.newTabSpec("Physics")
        spec.setContent(R.id.tab2)
        spec.setIndicator("Physics")
        host.addTab(spec)

        //Tab 3d
        spec = host.newTabSpec("Marvel Super Heroes")
        spec.setContent(R.id.tab3)
        spec.setIndicator("Marvel Super Heroes")
        host.addTab(spec)

        host.setOnTabChangedListener({ tabId ->
            val i = Intent(this, TopicActivity::class.java)

            val topic: LinearLayout
            if (tabId.equals("tab1")) {
                topic = findViewById(R.id.tab1)
            } else if (tabId.equals("tab2")) {
                topic = findViewById(R.id.tab2)
            } else {
                topic = findViewById(R.id.tab3)
            }
            i.putExtra("EXTRA_TEXT", topic.toString())
            startActivity(i)
        })
    }

    fun subject() : String {
        return subject
    }
}
