package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentProjectionsBinding
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos.ProjectionsFragmentViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectionsFragment : Fragment() {
    private val viewModel: ProjectionsFragmentViewModel by viewModels()
    private lateinit var binding: FragmentProjectionsBinding

    companion object {
        const val DISPUTA_DA_SPIKE = 0
        const val SEM_CLASSIFICACAO = 1
        const val DISPARADA = 2
        const val MATA_MATA = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: 0
                setListeners(position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setListeners(position: Int = DISPUTA_DA_SPIKE) {
        viewModel.previsoesDosJogos(position).observe(viewLifecycleOwner, Observer {
            binding.previsoesJogos = it
        })
    }

    override fun toString(): String {
        return getString(R.string.jogos)
    }
}
