package com.example.valorantpassbattle.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import java.util.*


class InputActivity : AppCompatActivity() {
    lateinit var passBattle: PassBattle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        passBattle = PassBattleFactory(this).getPassBattle()

        val etInitialData = findViewById<EditText>(R.id.activity_input_et_initial_date)
        createDataPicker(etInitialData)
    }

    fun createDataPicker(et: EditText){
        et.setInputType(InputType.TYPE_NULL)
        et.setOnClickListener {
            val cldr: Calendar = passBattle.dateInit
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            // date picker dialog
            val picker = DatePickerDialog(
                this@InputActivity,
                { view, year, monthOfYear, dayOfMonth -> et.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                year,
                month - 1,
                day
            )
            picker.show()
        }
    }
}