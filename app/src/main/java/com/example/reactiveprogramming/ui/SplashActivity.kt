package com.example.reactiveprogramming.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.reactiveprogramming.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())

        //Here we create the splash screen activity and the delay time
        handler.postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}