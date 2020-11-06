package com.example.valorantpassbattle.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.Historic.UserInputsTier
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import com.example.valorantpassbattle.model.Properties.Properties
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tierinput_dialog.view.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var context: Context

        lateinit var historic: Historic
        lateinit var passBattle: PassBattle
        lateinit var properties: Properties

        fun setContext(con: Context) {
            context = con
            historic = Historic(context)
            passBattle = PassBattleFactory(context).getPassBattle()
            properties = Properties(historic, passBattle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContext(this);
        createListeners()
    }

    private fun createListeners() {
        createNavigationItemSelectedListener()
        fab.setOnClickListener(this)
    }

    private fun createNavigationItemSelectedListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val previousItem = bottomNavigationView.selectedItemId
            val nextItem = item.itemId
            if (previousItem != nextItem) {
                when (nextItem) {
                    R.id.item_bar_chart -> {
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Chart")

                    }
                    R.id.item_timeline -> {
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Timeline")
                    }
                    R.id.item_timer -> {
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Timer")
                    }
                    R.id.item_apps -> {
                        Log.i("ItemSelected", "createNavigationItemSelectedListener: Apps")
                        bottomNavigationView.transform(fab)
                    }
                }

            }
            true
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> openDialogInputTier()
        }
    }

    fun openDialogInputTier() {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tierinput_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Tier Form")
        //show dialog
        val mAlertDialog = mBuilder.show()

        //login button click of custom layout
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val tier = mDialogView.tierinput_dialog_et_level_current.text.toString().toInt()
            val expMissing = mDialogView.tierinput_dialog_et_exp_missing.text.toString().toInt()
            val inputUser = UserInputsTier(tier, expMissing)
            historic.add(inputUser)
        }
        //cancel button click of custom layout
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }
}