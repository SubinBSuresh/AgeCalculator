package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btn_datePicker)
        tvSelectedDate = findViewById(R.id.tv_selectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    //Function for DatePicker
    private fun clickDatePicker() {
        //Get current date
        val currentCalendar = Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val date = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
                Toast.makeText(
                    this,
                    "year was $selectedYear, month was ${selectedMonth + 1}, date was $dayOfMonth",
                    Toast.LENGTH_SHORT
                ).show()

                val selectedDate = "$dayOfMonth/$selectedMonth/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentTime =
                        simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                    currentTime?.let {
                        val currentDateInMInutes = currentTime.time / 60000
                        val differenceInMInutes = currentDateInMInutes - selectedDateInMinutes

                        tvSelectedDateInMinutes?.text = differenceInMInutes.toString()
                    }
                }
            },
            year,
            month,
            date
        )

        datePicker.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePicker.show()


    }
}