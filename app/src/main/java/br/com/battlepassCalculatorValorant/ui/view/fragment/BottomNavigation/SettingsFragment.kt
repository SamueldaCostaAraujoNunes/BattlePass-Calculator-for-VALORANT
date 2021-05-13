package br.com.battlepassCalculatorValorant.ui.view.fragment.BottomNavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.theme.Theme
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.bottomNavigation.SettingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    private val viewModel: SettingsFragmentViewModel by viewModels()
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPreferencesListeners()
    }

    private fun setPreferencesListeners() {

        viewModel.lastTier.observe(viewLifecycleOwner, { ultimoTier ->
            if (ultimoTier.tierCurrent > 50 && epilogoPreference?.isChecked!!) {
                epilogoPreference?.isEnabled = false
            }
        })

        themePreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                Theme(requireContext()).setThemeMode(newValue.toString().toInt())
                true
            }

//        epilogoPreference?.onPreferenceChangeListener =
//            OnPreferenceChangeListener { _, newValue ->
//                val value = newValue.toString().toBoolean()
//
//                val realValue =
//                    if (ultimoTier > 50) {
//                        true
//                    } else {
//                        value
//                    }
//                properties.passBattle.espilogoIsValide = realValue
//                if (ultimoTier > 50 && epilogoPreference?.isChecked!!) {
//                    epilogoPreference?.isEnabled = false
//                }
//                properties.historic.sendUpdateEvent()
//                true
//            }

        editHistoric?.setOnPreferenceClickListener {
            val findNavController = findNavController()
            val direction =
                SettingsFragmentDirections.actionSettingsFragmentToHistoricInputFragment()
            findNavController.navigate(direction)
            true
        }
    }

}

