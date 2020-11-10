package com.example.valorantpassbattle.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.ColorFromXml
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.Historic.UserInputsTier
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import com.example.valorantpassbattle.model.Properties.Properties
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_title.view.*
import kotlinx.android.synthetic.main.tierinput_dialog.view.*


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
                        historic.clear()
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
        val inflater = this.layoutInflater

        val titleView: View = inflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = "Tier Form"

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tierinput_dialog, null)
        val tvTierIndex = mDialogView.tierinput_dialog_et_level_current
        val tvTierExpMissing = mDialogView.tierinput_dialog_et_exp_missing

        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setCustomTitle(titleView)
        //show dialog
        val mAlertDialog = mBuilder.show()
        //add listenerTxetChanged
//        tvTierIndex.addTextChangedListener(TextWatcherTierIndex(tvTierIndex))

        //login button click of custom layout
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            //get text from EditTexts of custom layout
            if (validadeTierIndex(tvTierIndex) and validadeTierExpMissing(tvTierExpMissing)) {
                val tier = tvTierIndex.text.toString().toInt()
                val expMissing = tvTierExpMissing.text.toString().toInt()
                val inputUser = UserInputsTier(tier, expMissing)
                historic.add(inputUser)
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }
        //cancel button click of custom layout
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    fun validadeTierIndex(tv: TextView): Boolean {
        val tierStr = tv.text.toString()
        val ultimoTier = if (historic.isEmpty()) 0 else historic.last().tierCurrent
        if (tierStr == "") tv.error = "Insira um tier!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 3) {
                val tierInt = tierStr.toInt()
                if ((tierInt <= 0) or (tierInt > 50)) tv.error = "Insira um tier entre 1 e 50!"
                if (tierInt <= ultimoTier) tv.error = "Insira um tier maior que ${ultimoTier}!"
            } else {
                tv.error = "Insira um tier menor que 50!"
            }
        }

        return tv.error == null
    }

    fun validadeTierExpMissing(tv: TextView): Boolean {
        val tierStr = tv.text.toString()
        if (tierStr == "") tv.error = "Insira o EXP faltando!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 5) {
                val tierInt = tierStr.toInt()
                if ((tierInt < 0) or (tierInt > 50000)) tv.error = "Insira um EXP entre 0 e 50000!"
            } else {
                tv.error = "Insira um EXP menor ou igual a 50000!"
            }
        }
        return tv.error == null
    }

}