package com.example.valorantpassbattle

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listaDeAlunos = mutableListOf<String>("Samuel", "Roberto", "Carlos", "Pato", "Daniel")
        val listView = findViewById<ListView>(R.id.activity_main_list_view)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaDeAlunos)
        listView.setAdapter(adapter)
    }
}