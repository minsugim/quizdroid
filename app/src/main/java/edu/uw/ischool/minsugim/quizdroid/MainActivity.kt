package edu.uw.ischool.minsugim.quizdroid

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.MenuInflater
import android.view.MenuItem
import android.os.Build
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.R.string.cancel
import android.R.string.ok
import android.content.*
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        var subjects : MutableList<String> = mutableListOf()
        for(topic in QuizApp.instance.getTopics()) {
            subjects.add(topic.title)
        }

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, subjects)
        listView.adapter = adapter
        listView.setOnItemClickListener({ _, _, position, _ ->
            val intent = Intent(this, ControllerActivity::class.java)
            intent.putExtra("EXTRA_TEXT", listView.getItemAtPosition(position).toString())
            intent.putExtra("TopicNumber", position)
            startActivity(intent)
        })

        if (isAirplaneModeOn(applicationContext)) {
            displayAlert()
        }

        registerReceiver(broadcastReceiver, IntentFilter("AIRPLANE_MODE"))
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            displayAlert()
        }
    }

    private fun displayAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            startActivityForResult(Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS), 0)
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
            dialog.cancel()
        })
        builder.setMessage("Airplane mode is on. Would you like to turn off Airplane mode?")
                .setTitle("Connectivity Issues")

        val dialog = builder.create()
        dialog.show()
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.Global.getInt(context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, Preferences::class.java)
            startActivity(intent)
            true
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }


    class NetworkChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if (!isConnected) {
                if (isAirplaneModeOn(context)) {
                    context.sendBroadcast(Intent("AIRPLANE_MODE"))
                }
            } else {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
            }
        }

        private fun isAirplaneModeOn(context: Context): Boolean {
            return Settings.Global.getInt(context.contentResolver,
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        }
    }

}
