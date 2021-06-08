package br.com.samuelnunes.valorantpassbattle.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.samuelnunes.valorantpassbattle.databinding.DialogNewsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DialogNews : DialogBase() {

    lateinit var binding: DialogNewsBinding
    private val args by navArgs<DialogNewsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogNewsBinding.inflate(inflater, container, false)
        binding.titleName = args.title
        binding.content = args.content
        return binding.root
    }

}