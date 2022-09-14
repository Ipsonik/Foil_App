package com.example.foliarz

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dashboard.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // -------------------------------Deklaracja CardView i animacje---------------------------------------
        initObjects()
        // ------------------------------Click Listener -----------------------------------------------------
        clicks()
    }
    override fun onStart() {
        super.onStart()
        initObjects()
        clicks()
    }

    override fun onRestart() {
        super.onRestart()
        initObjects()
        clicks()
    }
    override fun onResume() {
        super.onResume()
        initObjects()
        clicks()
    }

    private fun initObjects(){
        val cardView1 = findViewById<CardView>(R.id.cardView_create)
        cardviewanimations(cardView1, this, "left")

        val cardView2 = findViewById<CardView>(R.id.projects_CardView)
        cardviewanimations(cardView2, this, "right")

        val cardView3 = findViewById<CardView>(R.id.about_CardView)
        cardviewanimations(cardView3, this, "left")

        val cardView4 = findViewById<CardView>(R.id.calendar_CardView)
        cardviewanimations(cardView4, this, "right")

        val cardView5 = findViewById<CardView>(R.id.calculator_CardView)
        cardviewanimations(cardView5, this, "left")

        val cardView6 = findViewById<CardView>(R.id.numbers_CardView)
        cardviewanimations(cardView6, this, "right")

        val layout = findViewById<LinearLayout>(R.id.dashboard_layout)
        layoutanimation(layout, this)
    }
    private fun clicks(){
        //************************* Obsługa kliknięcia "Stwórz zamówienie" *************************
        cardView_create.setOnClickListener {

            // **************************   Wywołanie odpowiedniej aktywności **********************
            val intent = Intent(applicationContext, CreateActivity::class.java)
            startActivity(intent)
        }
        about_CardView.setOnClickListener {

            // **************************   Wywołanie odpowiedniej aktywności **********************
            val intent = Intent(applicationContext, AboutActivity::class.java)
            startActivity(intent)
        }
        calendar_CardView.setOnClickListener {

            // **************************   Wywołanie odpowiedniej aktywności **********************
            val intent = Intent(applicationContext, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}