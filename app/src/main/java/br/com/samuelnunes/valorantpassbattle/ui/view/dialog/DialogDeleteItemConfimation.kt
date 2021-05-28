package br.com.samuelnunes.valorantpassbattle.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.DialogConfirmationBinding
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.dialog.DialogDeleteItemConfimationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogDeleteItemConfimation :
    DialogBase() {
    private val viewModel: DialogDeleteItemConfimationViewModel by viewModels()
    lateinit var binding: DialogConfirmationBinding
    private val args by navArgs<DialogDeleteItemConfimationArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserTierById(args.userTierId)
            .observe(viewLifecycleOwner, Observer { userTier ->
                binding.mensageContent.text = getString(R.string.text_delete, userTier.tierCurrent)
                binding.btnSave.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.delete(userTier)
                    }
                    dismiss()
                }
                binding.btnCancel.setOnClickListener {
                    dismiss()
                }
            })
    }


}