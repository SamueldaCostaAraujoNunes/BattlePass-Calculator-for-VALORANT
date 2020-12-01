package br.com.battlepassCalculatorValorant.ui.activity.Settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import br.com.battlepassCalculatorValorant.ui.adapter.MyEditHistoricAdapter
import kotlinx.android.synthetic.main.activity_edit_historic.*

class EditHistoricActivity : AppCompatActivity() {
    val data = MainActivity.historic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_historic)

        lv_edit_historic.adapter = MyEditHistoricAdapter(this, data)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener { finish() }
    }
}