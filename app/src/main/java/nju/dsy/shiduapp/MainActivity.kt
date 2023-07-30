package nju.dsy.shiduapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    // tag
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v(TAG,"Main activity onCreate ; time = ${System.currentTimeMillis()}")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG,"Main activity onResume ; time = ${System.currentTimeMillis()}")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG,"Main activity onPause ; time = ${System.currentTimeMillis()}")
    }



}