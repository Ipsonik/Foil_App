package com.example.foliarz

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        home_image.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        pokaz_mape.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:52.40032030198386, 16.95574787153668")
            val chooser = Intent.createChooser(intent, "Włącz mapy")
            startActivity(chooser)
        }

        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:111111111")
            startActivity(intent)
        }

        email.setOnClickListener {
            val odbiorca = "phillipekcoutinho@gmail.com"
            val odbiorcy = arrayOf(odbiorca)
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, odbiorcy)
            startActivity(Intent.createChooser(intent, "Wyślij wiadomość email"))
        }
    }
}