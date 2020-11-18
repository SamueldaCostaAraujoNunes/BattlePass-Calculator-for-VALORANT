package br.com.battlepassCalculatorValorant.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.DataBase.MySharedPreferences
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import br.com.battlepassCalculatorValorant.model.PassBattle.PassBattleFactory
import kotlinx.android.synthetic.main.activity_first_input.*

class FirstInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_input)
        bt_save.setOnClickListener { buttonSaveClick() }
    }

    fun buttonSaveClick() {
        if (validadeTierIndex()) {
            if (validadeTierExpMissing()) {
                val tier = tierinput_dialog_et_level_current.text.toString().toInt()
                val expMissing = tierinput_dialog_et_exp_missing.text.toString().toInt()
                val inputUser = UserInputsTier(tier, expMissing)
                val historic = Historic(this)
                historic.add(inputUser)
//                val diariasCheck =  cb_diarias.isChecked
//                historic.missoesDiarias = diariasCheck
//                val semanaisCheck = cb_semanais.isChecked
//                historic.missoesSemanais = semanaisCheck
                mostrarProxActivity()
            }
        }
    }

    private fun mostrarProxActivity() {
        val bd = MySharedPreferences(this)
        bd.setBoolean(bd.keyFirstInputComplete, true)
        val intent = Intent(this@FirstInputActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun validadeTierIndex(): Boolean {
        var error: String? = null
        val tv = tierinput_dialog_et_level_current
        val tierStr = tv.text.toString()
        if (tierStr == "") error = "Insira um tier!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 3) {
                val tierInt = tierStr.toInt()
                if ((tierInt < 0) or (tierInt > 50)) error =
                    "Insira um tier entre 0 e 50!"
            } else {
                error = "Insira um tier menor que 50!"
            }
        }
        tv.error = error
        return error == null
    }

    fun validadeTierExpMissing(): Boolean {
        var error: String? = null
        val tv = tierinput_dialog_et_exp_missing
        val tierExpMissing = tv.text.toString()
        if (tierExpMissing == "") error = "Insira o EXP faltando!"

        val tier = tierinput_dialog_et_level_current
            .text
            .toString()
            .toInt()

        val xpMaxMissig = if ((0 < tier) and (tier <= 50)) PassBattleFactory(this)
            .getPassBattle()
            .getTier(tier)?.expMissing!! else 50000

        if (tierExpMissing.isNotEmpty()) {
            if (tierExpMissing.length <= 5) {
                val expMissing = tierExpMissing.toInt()
                if ((expMissing < 0) or (expMissing > xpMaxMissig)) error =
                    "Insira um EXP entre 0 e ${xpMaxMissig}!"
            } else {
                error = "Insira um EXP menor ou igual a ${xpMaxMissig}!"
            }
        }
        tv.error = error
        return error == null
    }
}