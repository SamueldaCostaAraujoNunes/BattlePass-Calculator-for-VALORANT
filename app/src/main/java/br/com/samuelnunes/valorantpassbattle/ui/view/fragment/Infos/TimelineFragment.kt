package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentTimelineBinding
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.FragmentWithTitle
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos.TimelineFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimelineFragment : FragmentWithTitle() {
    private val viewModel: TimelineFragmentViewModel by viewModels()
    private lateinit var binding: FragmentTimelineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override val titleResId: Int
        get() = R.string.linha_do_tempo

}