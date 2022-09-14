package com.example.foliarz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    private fun sendemail(name: EditText, numer: EditText, email: EditText, radioButton: RadioButton, spinner1: String, spinner2: String) {
        val odbiorca = "phillipekcoutinho@gmail.com"
        val to = arrayOf(odbiorca)
        val temat = "Zamówienie"
        val tresc =
            "Imię i nazwisko: " + name.text.toString() + "\r\n" + " Email: " + email.text.toString() + "\r\n" + "Numer telefonu: " + numer.text.toString() + "\r\n" +"Wariant folii: " + radioButton.text.toString() + "\r\n" + "Marka auta: " + spinner1 + "\r\n" + "Typ nadwozia: " + spinner2

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_EMAIL, to)
        intent.putExtra(Intent.EXTRA_SUBJECT, temat)
        intent.putExtra(Intent.EXTRA_TEXT, tresc)

        startActivity(Intent.createChooser(intent, "wybierz: "))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val name = findViewById<EditText>(R.id.imie_i_nazwisko_edittext)
        val email = findViewById<EditText>(R.id.email_edittext)
        val number = findViewById<EditText>(R.id.numer_telefonu_edittext)
        val uwagi = findViewById<EditText>(R.id.uwagi_edittext)
        val country_code = findViewById<CountryCodePicker>(R.id.countryCodeHolder)
        val radiogroup = findViewById<RadioGroup>(R.id.radiogroup)
        //val radiobutton_folia = findViewById(R.id.radiobutton_swojafolia) as RadioButton
        val radiobutton_gotowy = findViewById(R.id.radiobutton_gotowy) as RadioButton
        val id_gotowy = R.id.radiobutton_gotowy
        //val radiobutton_projekt = findViewById(R.id.radiobutton_projekt) as RadioButton
        val wariant_TextView = findViewById<TextView>(R.id.wariantfolii)
        val car_spinner = findViewById<Spinner>(R.id.cars_spinner)
        val nadwozie_spinner = findViewById<Spinner>(R.id.models_spinner)




        //-------------------------------Powrót do Dashboard ---------------------------------------

        homeicon.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        //------------------------------Sprawdzanie EditText ---------------------------------------
        namevalidation(name)
        emailvalidation(email)
        phonevalidation(number)
        radiogroupvalidation(radiogroup, wariant_TextView, id_gotowy, uwagi)


        //val radiobutton = findViewById<RadioButton>(radiogroup.checkedRadioButtonId)
        //------------------------------ Wysłanie maila --------------------------------------------

        zatwierdz_button.setOnClickListener {

            val car_spinner_text = car_spinner.selectedItem.toString()
            val nadwozie_spinner_text = nadwozie_spinner.selectedItem.toString()
            if(namevalidation(name) && emailvalidation(email) && phonevalidation(number) && radiogroupvalidation(radiogroup,wariant_TextView, id_gotowy, uwagi_edittext)) {
                val rbid = radiogroup.checkedRadioButtonId
                val checkedradiobutton = findViewById(rbid) as RadioButton
                sendemail(name, number, email, checkedradiobutton, car_spinner_text, nadwozie_spinner_text)
            } else {
                if(name.text.isEmpty()) name.error = "Nie wprowadzono danych"
                if(number.text.isEmpty()) number.error = "Nie wprowadzono danych"
                if(email.text.isEmpty()) email.error = "Nie wprowadzono danych"
                if(radiogroup.checkedRadioButtonId == -1) wariant_TextView.error = "Nie zaznaczono opcji"
                if(radiogroup.checkedRadioButtonId == id_gotowy) uwagi_edittext.error = "Nie wpisano numeru"
            }
        }

    }
}


