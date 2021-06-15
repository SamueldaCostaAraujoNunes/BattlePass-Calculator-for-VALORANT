package br.com.samuelnunes.valorantpassbattle.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.samuelnunes.valorantpassbattle.databinding.DialogTierinputBinding
import br.com.samuelnunes.valorantpassbattle.model.dto.FormInput
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier
import br.com.samuelnunes.valorantpassbattle.notification.Notification
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.AdmobViewModel
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.dialog.InputUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogInput : DialogBase() {

    lateinit var binding: DialogTierinputBinding
    private val viewModel: InputUserViewModel by viewModels()
    private val admobViewModel: AdmobViewModel by activityViewModels()
    private val args by navArgs<DialogInputArgs>()
    private var idUserInput: Int? = null
    private val notification: Notification by lazy {
        Notification(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogTierinputBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setOnClickListener()
        setArgsField()
        return binding.root
    }

    private fun setArgsField() {
        val id: Int = args.userTierId
        if (id != -1) {
            viewModel.getUserTierById(id).observe(viewLifecycleOwner, Observer {
                binding.tietTierCurrent.setText(it.tierCurrent.toString())
                binding.tietExpMissing.setText(it.expCurrent.toString())
                this.idUserInput = id
            })
        }
    }

    private fun setOnClickListener() {
        binding.btnSave.setOnClickListener {
            val form = FormInput(
                binding.tietTierCurrent.text.toString(),
                binding.tietExpMissing.text.toString()
            )
            if (viewModel.validador(form)) {
                val userTier = if (idUserInput != null) {
                    UserTier(form.tierCurrent.toInt(), form.expCurrent.toInt(), idUserInput!!)
                } else {
                    UserTier(form.tierCurrent.toInt(), form.expCurrent.toInt())
                }
                CoroutineScope(IO).launch {
                    viewModel.save(userTier)
                }
                dismiss()
                notification.create()
                admobViewModel.showInterstitial()
            }
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }

}

