package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Settings

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.samuelnunes.valorantpassbattle.NavGraphDirections
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentDeveloperInfosBinding
import br.com.samuelnunes.valorantpassbattle.extensions.copyToClipboard
import br.com.samuelnunes.valorantpassbattle.extensions.showSnack
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeveloperInfosFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDeveloperInfosBinding

    private val navController: NavController
        get() {
            return findNavController()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDeveloperInfosBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.SheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.github.setOnClickListener {
            navController.navigate(NavGraphDirections.actionGlobalGithub())
            dismiss()
        }
        binding.linkedin.setOnClickListener {
            navController.navigate(NavGraphDirections.actionGlobalLinkedin())
            dismiss()
        }
        binding.gmail.setOnClickListener {
            requireContext().copyToClipboard(getString(R.string.gmail))
            showSnack("Email Copiado", Snackbar.LENGTH_SHORT)
            dismiss()
        }
        binding.kofi.setOnClickListener {
            navController.navigate(NavGraphDirections.actionGlobalKofi())
            dismiss()
        }
    }
}