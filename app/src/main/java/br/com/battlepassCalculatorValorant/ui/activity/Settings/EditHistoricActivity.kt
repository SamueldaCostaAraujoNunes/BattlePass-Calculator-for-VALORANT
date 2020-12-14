package br.com.battlepassCalculatorValorant.ui.activity.Settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerHistoric
import br.com.battlepassCalculatorValorant.ui.adapter.MyEditHistoricAdapter
import kotlinx.android.synthetic.main.activity_edit_historic.*

class EditHistoricActivity : AppCompatActivity() {

    val historic: Historic = ManagerHistoric.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_historic)

        lv_edit_historic.adapter = MyEditHistoricAdapter(this, historic)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener { finish() }
    }
}