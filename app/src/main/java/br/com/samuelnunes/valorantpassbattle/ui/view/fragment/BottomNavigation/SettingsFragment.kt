package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.BottomNavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.database.dataStore.SettingsDataStore
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.bottomNavigation.SettingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    private val viewModel: SettingsFragmentViewModel by viewModels()
    private val themePreference by lazy {
        findPreference<Preference>(getString(R.string.themeStatus))
    }
    private val epilogoPreference by lazy {
        findPreference<SwitchPreferenceCompat>(getString(R.string.epilogue))
    }
    private val editHistoric by lazy {
        findPreference<Preference>(getString(R.string.editHistoric))
    }

    private val bugReport by lazy {
        findPreference<Preference>(getString(R.string.bugReport))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = settingsDataStore
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPreferencesListeners()
    }

    private val navController: NavController
        get() {
            return findNavController()
        }


    private fun setPreferencesListeners() {

        viewModel.lastTier.observe(viewLifecycleOwner, { ultimoTier ->
            if (ultimoTier.tierCurrent > 50) {
                epilogoPreference?.isChecked = true
                epilogoPreference?.isEnabled = false
            }
        })

        themePreference?.setOnPreferenceClickListener {
            val direction = SettingsFragmentDirections.actionSettingsFragmentToDialogChooseThemes()
            navController.navigate(direction)
            true
        }

        editHistoric?.setOnPreferenceClickListener {
            val direction =
                SettingsFragmentDirections.actionSettingsFragmentToHistoricInputFragment()
            navController.navigate(direction)
            true
        }

        bugReport?.setOnPreferenceClickListener {
            val direction =
                SettingsFragmentDirections.actionSettingsFragmentToDialogFormReportBugs()
            navController.navigate(direction)
            true
        }
    }

}

