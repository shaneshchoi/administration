package com.example.administration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        //Image animation (rotating loading screen)
        var img : ImageView = findViewById(R.id.loadingImage)
        var rotation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        img.startAnimation(rotation)
        delay()
    }
    //Giving delay when started.
    private fun delay() = Handler().postDelayed({
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }, 2300)
}