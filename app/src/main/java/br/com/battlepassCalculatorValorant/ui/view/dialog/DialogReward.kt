package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.databinding.DialogRewardBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.view.adapter.ImageSliderAdapter

class DialogReward(context: Context, reward: Reward) : AlertDialog(context) {
    private var builder: Builder

    init {
        builder = Builder(context)
            .setView(createContent(reward))
            .setTitle(Reward.getFonteTranslated(context, reward.fonte) + " " + reward.position)
    }

    private fun createContent(reward: Reward): View {
        val binding = DialogRewardBinding.inflate(layoutInflater, null)
        binding.reward = reward
        val mViewPagerAdapter = ImageSliderAdapter(reward.imagens)
        binding.dialogViewPagerMain.adapter = mViewPagerAdapter
        binding.indicatorImageSlider.setViewPager2(binding.dialogViewPagerMain)
        return binding.root
    }

    override fun show() {
        builder.show()
    }

}