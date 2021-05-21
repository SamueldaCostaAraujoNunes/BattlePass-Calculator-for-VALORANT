package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.battlepassCalculatorValorant.databinding.DialogRewardBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.view.adapter.ImageSliderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogReward(val reward: Reward) : DialogBase() {

    init {
        TODO("Adicionar um construtor compativel com o navigation")
    }

    lateinit var binding: DialogRewardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogRewardBinding.inflate(inflater, container, false)
        binding.reward = reward
        binding.titleName =
            Reward.getFonteTranslated(requireContext(), reward.fonte) + " " + reward.position
        val mViewPagerAdapter = ImageSliderAdapter(reward.imagens)
        binding.dialogViewPagerMain.adapter = mViewPagerAdapter
        binding.indicatorImageSlider.setViewPager2(binding.dialogViewPagerMain)
        return binding.root
    }

}