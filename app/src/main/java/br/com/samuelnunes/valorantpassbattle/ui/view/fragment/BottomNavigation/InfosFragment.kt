package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentInfosBinding
import br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters.load

class InfosFragment : Fragment() {

    private lateinit var binding: FragmentInfosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adViewTop.load()
        binding.adViewCenter.load()
        binding.adViewBottom.load()
    }
}