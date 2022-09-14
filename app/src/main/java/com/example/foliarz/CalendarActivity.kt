package com.example.foliarz

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    @RequiresApi(Build.VERSION_CODES.O)
    lateinit var selectedDate: LocalDate
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthYearText: TextView
    private lateinit var eventButton: Button
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private var addButtonPressed: Boolean = false
    private var deleteButtonPressed: Boolean = false
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var dbEventArray: ArrayList<String>
    private val dbDate = hashMapOf<String, Any>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        initObjects()
        buttonsCheck()
        getFromDataBase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRestart() {
        super.onRestart()
        initObjects()
        buttonsCheck()
        getFromDataBase()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initObjects() {
        eventButton = findViewById(R.id.addEventButton)
        saveButton = findViewById(R.id.saveEvent)
        deleteButton = findViewById(R.id.deleteEvent)
        monthYearText = findViewById(R.id.monthYearText)
        recyclerView = findViewById(R.id.calendarRecyclerView)
        selectedDate = LocalDate.now()
        dbEventArray = ArrayList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val days: ArrayList<LocalDate> = daysInMonthArray(selectedDate)
        val calendarAdapter = CalendarAdapter(days, this, selectedDate, dbEventArray)
        val layoutManager = GridLayoutManager(applicationContext, 7)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun daysInMonthArray(date: LocalDate): ArrayList<LocalDate> {
        val daysInMonthArrayVar = ArrayList<LocalDate>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                daysInMonthArrayVar.add(LocalDate.of(1930, 9, 30))
            } else {
                daysInMonthArrayVar.add(
                    LocalDate.of(
                        selectedDate.year,
                        selectedDate.month,
                        i - dayOfWeek
                    )
                )
            }
        }
        return daysInMonthArrayVar
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthFunction(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addToDatabase(date: LocalDate) {
        dbDate["date"] = date.toString()
        db.collection("busydays").document(date.toString())
            .set(dbDate)
            .addOnSuccessListener {
              //  Toast.makeText(this, "Record added succesfully $date", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                //Toast.makeText(this, "Record added unsuccesfully $date", Toast.LENGTH_SHORT).show()
            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFromDataBase() {
        dbEventArray.clear()
        db.collection("busydays").addSnapshotListener { value, e ->
            if (e != null) {
                Toast.makeText(this, "listen failed", Toast.LENGTH_SHORT)
                return@addSnapshotListener
            }
            for (doc in value!!) {
                doc.getString("date")?.let {
                    dbEventArray.add(it)
                }
            }
            setMonthView()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteFromDataBase(date: LocalDate) {
        db.collection("busydays").document(date.toString()).delete().addOnSuccessListener {
            //Toast.makeText(this, "Record removed succesfully $date", Toast.LENGTH_SHORT).show()

        }
            .addOnFailureListener {
                //Toast.makeText(this, "Record removed unsuccesfully $date", Toast.LENGTH_SHORT).show()
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buttonsCheck() {
        eventButton.setOnClickListener {
            addButtonPressed = true
            deleteButtonPressed = false
            saveButton.visibility = View.VISIBLE
            deleteButton.visibility = View.VISIBLE
        }
        saveButton.setOnClickListener {
            addButtonPressed = false
            deleteButtonPressed = false
            saveButton.visibility = View.GONE
            deleteButton.visibility = View.GONE
            selectedDate = LocalDate.now()
            setMonthView()
        }
        deleteButton.setOnClickListener {
            deleteButtonPressed = true
            addButtonPressed = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate) {
        if (date != LocalDate.of(1930, 9, 30)) {
            selectedDate = date
            if (addButtonPressed) addToDatabase(date)
            if (deleteButtonPressed) deleteFromDataBase(date)
        }
        getFromDataBase()

    }
}