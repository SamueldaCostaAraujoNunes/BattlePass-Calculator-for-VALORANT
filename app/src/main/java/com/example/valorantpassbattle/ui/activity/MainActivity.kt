package com.example.valorantpassbattle.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.ColorFromXml
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import com.example.valorantpassbattle.model.Properties.Properties
import com.example.valorantpassbattle.ui.dialog.DialogInput
import com.example.valorantpassbattle.ui.fragment.ChartsFragment
import com.example.valorantpassbattle.ui.fragment.InfosFragment
import com.example.valorantpassbattle.ui.fragment.PrincipalFragment
import com.example.valorantpassbattle.ui.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var context: Context

        lateinit var historic: Historic
        lateinit var passBattle: PassBattle
        lateinit var properties: Properties
        lateinit var colorXML: ColorFromXml

        fun setContext(con: Context) {
            context = con
            historic = Historic(context)
            passBattle = PassBattleFactory(context).getPassBattle()
            properties = Properties(historic, passBattle)
            colorXML = ColorFromXml(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment(R.id.fragmentPrincipal, PrincipalFragment())
        setContext(this)
        createListeners()
    }

    private fun createFragment(layout: Int, fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(layout, fragment).commit()
    }

    private fun createListeners() {
        createNavigationItemSelectedListener()
        fab.setOnClickListener(this)
    }

    private fun createNavigationItemSelectedListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val previousItem = bottomNavigationView.selectedItemId
            val nextItem = item.itemId
            var fragment: androidx.fragment.app.Fragment? = null
            if (previousItem != nextItem) {
                when (nextItem) {
                    R.id.item_home -> {
                        fragment = PrincipalFragment()
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Home")
                    }
                    R.id.item_timeline -> {
                        fragment = ChartsFragment()
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Timeline")
                    }
                    R.id.item_timer -> {
                        fragment = InfosFragment()
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Timer")
                    }
                    R.id.item_apps -> {
                        fragment = SettingsFragment()
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Apps")
                    }
                }
                bottomNavigationView.transform(fab, nextItem != R.id.item_apps)
                createFragment(R.id.fragmentPrincipal, fragment!!)
            }
            true
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> DialogInput(this).show()
        }
    }
}