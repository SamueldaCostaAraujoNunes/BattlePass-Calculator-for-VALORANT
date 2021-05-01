package br.com.battlepassCalculatorValorant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.databinding.FragmentHomeBinding
import br.com.battlepassCalculatorValorant.extensions.bindingAdapters.load
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties


class HomeFragment : Fragment() {
    private lateinit var properties: Properties
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adViewTop.load()
        binding.adViewCenter.load()
        binding.adViewBottom.load()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        properties.historic.deleteAll()
    }
}