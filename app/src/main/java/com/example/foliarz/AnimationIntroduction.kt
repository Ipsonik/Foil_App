package com.example.foliarz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class AnimationIntroduction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_introduction)

        val logo = findViewById<ImageView>(R.id.carlogo)
        val napis = findViewById<TextView>(R.id.napis)
        val lottieres = findViewById<LottieAnimationView>(R.id.lottieres)
        val background = findViewById<ImageView>(R.id.backgroundimage)
        val animacja = AnimationUtils.loadAnimation(this, R.anim.introscreen)
        napis.startAnimation(animacja)
        logo.startAnimation(animacja)
        lottieres.startAnimation(animacja)

        animacja.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                lottieres.visibility = View.GONE
                napis.visibility = View.GONE
                logo.visibility = View.GONE
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }
}