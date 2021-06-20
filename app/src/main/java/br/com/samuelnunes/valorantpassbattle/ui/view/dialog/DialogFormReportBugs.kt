package br.com.samuelnunes.valorantpassbattle.ui.view.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.DialogFormReportBugsBinding
import br.com.samuelnunes.valorantpassbattle.extensions.capitalize
import br.com.samuelnunes.valorantpassbattle.model.dto.FormQuestion
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.UIViewModel
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.dialog.DialogFormReportBugsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class DialogFormReportBugs : DialogBase() {
    lateinit var binding: DialogFormReportBugsBinding
    private val uiViewModel: UIViewModel by activityViewModels()
    private val viewModel: DialogFormReportBugsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogFormReportBugsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnSave.setOnClickListener {
            if (binding.tietBugs.text.toString().isNotEmpty() ||
                binding.tietIdeas.text.toString().isNotEmpty()
            ) {
                lifecycleScope.launch {
                    viewModel.submitAnswers(
                        listOf(
                            FormQuestion(
                                getVersionName(),
                                getString(R.string.version_app)
                            ),
                            FormQuestion(
                                getDeviceName(),
                                getString(R.string.device_model)
                            ),
                            FormQuestion(
                                getLanguage(),
                                getString(R.string.language)
                            ),
                            FormQuestion(
                                getIdBattlePass(),
                                getString(R.string.battlepass_id)
                            ),
                            FormQuestion(
                                binding.tietBugs.text.toString(),
                                getString(R.string.bug)
                            ),
                            FormQuestion(
                                binding.tietIdeas.text.toString(),
                                getString(R.string.idea)
                            )
                        )
                    )
                }
            }
            dismiss()
            uiViewModel.sendNewMensageSnackbar(getString(R.string.feedback))
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    private fun getVersionName(): String {
        val manager = requireContext().packageManager
        val info = manager.getPackageInfo(
            requireContext().packageName, 0
        )
        return info.versionName
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model.capitalize()
        } else manufacturer.capitalize() + " " + model
    }

    private fun getLanguage(): String {
        return Locale.getDefault().displayLanguage.capitalize()
    }

    private fun getIdBattlePass(): String {
        return viewModel.getIdBattlePass()
    }
}

