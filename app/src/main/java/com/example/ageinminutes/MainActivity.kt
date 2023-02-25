package com.example.ageinminutes

import android.app.DatePickerDialog
//import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn=findViewById<Button>(R.id.btnDatePicker)

        btn.setOnClickListener {view ->
            clickDatePicker(view)
        }


    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance();
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


       DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener {
               view, selectedYear, selectedMonth, selectedDayOfMonth ->
               Toast.makeText(this, "Chosen year is $selectedYear , the month is $selectedMonth and the day is $selectedDayOfMonth " + year,
                   Toast.LENGTH_LONG).show()

               val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
               var tvSelect = findViewById<TextView>(R.id.tvSelectedDate)
               tvSelect.setText(selectedDate)

               val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)


               val theDate = sdf.parse(selectedDate)  //parse method return a date object

               val selectedDateInMinutes = theDate!!.time / 60000   // we use !! for checking the error instead of try catch

               val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))

               val currentDateToMinutes = currentdate!!.time / 60000

               val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

              var tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)

               if (differenceInMinutes < 0){
                   tvSelectedDateInMinutes.setText(("please select the valid date"))
               }else{
                   tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
               }


       },
           year, month, day).show()
    }
}