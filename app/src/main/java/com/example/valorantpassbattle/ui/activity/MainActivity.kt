package com.example.valorantpassbattle.ui.activity

import FabBottomNavigationView.FabBottomNavigationView
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(this)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val previousItem = bottomNavigationView.selectedItemId
            val nextItem = item.itemId
            if (previousItem != nextItem) {
                when (nextItem) {
                    R.id.item_bar_chart -> {
                        tv_fragment.setText("Charts")
                    }
                    R.id.item_timeline -> {
                        tv_fragment.setText("Timeline")
                    }
                    R.id.item_timer -> {
                        tv_fragment.setText("Timer")
                    }
                    R.id.item_apps -> {
                        tv_fragment.setText("Apps")
                    }
                }
            }
            true
        }
        fab.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button -> bottomNavigationView.transform(fab)
            R.id.fab -> tv_fragment.setText("Clicado")
        }
    }

}