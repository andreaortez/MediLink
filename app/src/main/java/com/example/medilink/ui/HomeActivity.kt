package com.example.medilink.ui

import DayCalendarAdapter
import DayUi
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medilink.R // Important: Import R from your app's package
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rvDays = findViewById<RecyclerView>(R.id.rvDays)
        val tvToday = findViewById<TextView>(R.id.tvToday)

        rvDays.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val today = LocalDate.now()

        // 5 días, con HOY en el centro (posición 2)
        val days = listOf(
            DayUi(today.minusDays(2)),
            DayUi(today.minusDays(1)),
            DayUi(today),
            DayUi(today.plusDays(1)),
            DayUi(today.plusDays(2))
        )

        val centerIndex = 2

        val adapter = DayCalendarAdapter(
            days = days,
            onDaySelected = { selectedDate ->
                val localeEs = Locale("es", "ES")
                tvToday.text = "Hoy, " +
                        selectedDate.format(DateTimeFormatter.ofPattern("dd MMM"))
            },
            initialSelectedIndex = centerIndex,
        )

        rvDays.adapter = adapter


        rvDays.post {
            rvDays.scrollToPosition(centerIndex)
        }
    }
    }

