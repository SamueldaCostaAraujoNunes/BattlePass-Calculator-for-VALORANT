package br.com.battlepassCalculatorValorant.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.theme.Theme
import br.com.battlepassCalculatorValorant.ui.activity.Settings.EditHistoricActivity


class SettingsFragment : PreferenceFragmentCompat() {
    private val themePreference by lazy {
        findPreference<ListPreference>(getString(R.string.themeStatus))
    }
    private val epilogoPreference by lazy {
        findPreference<SwitchPreferenceCompat>(getString(R.string.epilogoIsValide))
    }
    private val editHistoric by lazy {
        findPreference<Preference>(getString(R.string.editHistoric))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        setPreferencesListeners()
    }

    private fun setPreferencesListeners() {

        val properties = Properties(requireContext())
        val ultimoTier =
            if (properties.historic.isEmpty()) 0 else properties.historic.last().tierCurrent
        if (ultimoTier > 50 && epilogoPreference?.isChecked!!) {
            epilogoPreference?.isEnabled = false
        }

        themePreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { preference, newValue ->
                Theme(requireContext()).setThemeMode(newValue.toString().toInt())
                true
            }

        epilogoPreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { preference, newValue ->
                val value = newValue.toString().toBoolean()

                val realValue =
                    if (ultimoTier > 50) {
                        true
                    } else {
                        value
                    }
                properties.passBattle.espilogoIsValide = realValue
                if (ultimoTier > 50 && epilogoPreference?.isChecked!!) {
                    epilogoPreference?.isEnabled = false
                }
                properties.historic.sendUpdateEvent()
                true
            }
        editHistoric?.setOnPreferenceClickListener {
            val intent = Intent(requireContext(), EditHistoricActivity::class.java)
            startActivity(intent)
            true
        }
    }

}

