package br.com.battlepassCalculatorValorant.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerHistoric
import br.com.battlepassCalculatorValorant.ui.activity.Settings.EditHistoricActivity
import br.com.battlepassCalculatorValorant.ui.theme.Theme
import kotlinx.android.synthetic.main.dialog_title.view.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    lateinit var theme: Theme
    lateinit var historic: Historic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historic = ManagerHistoric.getInstance(requireContext())
        theme = Theme(requireContext())
    }

    override fun onResume() {
        super.onResume()
        clear_historic.setOnClickListener { historic.clear() }
        btn_change_theme.setOnClickListener { chooseThemeDialog() }
        btn_edit_historic.setOnClickListener {
            val intent = Intent(requireContext(), EditHistoricActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        historic.deleteAll()
    }

    private fun chooseThemeDialog() {
        val builder = AlertDialog.Builder(context, R.style.alertDialog)
            .setCustomTitle(createTitle("Theme Mode"))
        val checkedItem = theme.getThemeMode()

        builder.setSingleChoiceItems(theme.styles, checkedItem) { dialog, which ->
            theme.setThemeMode(which)
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun createTitle(title: String): View {
        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = title
        return titleView
    }

}
