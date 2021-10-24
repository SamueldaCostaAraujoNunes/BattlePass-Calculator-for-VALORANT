package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentProjectionsBinding
import br.com.samuelnunes.valorantpassbattle.extensions.addGameTypeItem
import br.com.samuelnunes.valorantpassbattle.model.GameType.GameType
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.FragmentWithTitle
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos.ProjectionsFragmentViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectionsFragment : FragmentWithTitle() {
    private val viewModel: ProjectionsFragmentViewModel by viewModels()
    private lateinit var binding: FragmentProjectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        addTabs()
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

    private fun addTabs() {
        enumValues<GameType>().forEach {
            binding.tabLayout.addGameTypeItem(it)
        }
    }

    private fun setListeners(position: Int = 0) {
        val gameType: GameType = GameType.values()[position]
        viewModel.previsoesDosJogos(gameType).observe(viewLifecycleOwner, Observer {
            binding.previsoesJogos = it
        })
    }

    override val titleResId: Int
        get() = R.string.jogos

}
