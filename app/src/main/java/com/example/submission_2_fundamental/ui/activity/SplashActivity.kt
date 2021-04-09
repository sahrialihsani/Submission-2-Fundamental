package com.example.submission_2_fundamental.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.submission_2_fundamental.R

class SplashActivity : AppCompatActivity() {
    private lateinit var handler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSupportActionBar()?.hide()
        handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this,
                MainActivity::class.java )
            startActivity(intent)
            finish()
        },2000)
    }
}