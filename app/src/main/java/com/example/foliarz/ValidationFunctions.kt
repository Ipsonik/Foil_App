package com.example.foliarz

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.w3c.dom.Text

fun namevalidation(name: EditText): Boolean {

    name.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name_val = name.text.toString().trim()
            if (name_val.isEmpty()) {
                name.error = "Nie może być pusto"
            } else {
                for (i in name.text) {
                    if (i == ' ') {
                        name.error = null
                        break
                    } else {
                        name.error = "Nie poprawne dane"
                    }
                }
            }
        }
    })

    return if (name.text.toString().isNotEmpty()) {
        name.error == null
    }else false
}

fun emailvalidation(email: EditText): Boolean {
    email.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name_val = email.text.toString().trim()
            if (name_val.isEmpty()) {
                email.error = "Nie może być pusto"
            } else {
                for (i in email.text) {
                    if (i == '@') {
                        email.error = null
                        break
                    } else {
                        email.error = "Brak @ w adresie email"
                    }
                }
            }
        }
    })
    return if (email.text.toString().isNotEmpty()) {
        email.error == null
    }else false
}

fun phonevalidation(phone: EditText): Boolean {


    phone.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name_val = phone.text.toString().trim()
            val sprawdzanie = "^[0-9]$"
            if (name_val.isEmpty()) {
                phone.error = "Nie może być pusto"
            } else {
                if (name_val.length > 8) {
                    phone.error = null
                } else {
                    phone.error = "Numer jest za krótki"
                }
            }
        }
    })
    return if (phone.text.toString().isNotEmpty()) {
        phone.error == null
    }else false

}

fun radiogroupvalidation(radiogroup: RadioGroup, warianty: TextView, id_gotowy: Int, uwagi_textview: EditText) : Boolean {
    radiogroup.setOnCheckedChangeListener { group, checkedId ->
        run {
            if (radiogroup.checkedRadioButtonId != -1) {
                if(checkedId == id_gotowy) {
                    if(uwagi_textview.text.isNotEmpty()) {
                        warianty.error = null
                        uwagi_textview.error= null
                    } else {
                     uwagi_textview.error = "Nie wpisano numeru"
                    }
                }else {
                    warianty.error = null
                    uwagi_textview.error = null
                }

            } else {
                warianty.error = "Nic nie zostało zaznaczone"
            }
        }
    }
    return warianty.error == null
}


