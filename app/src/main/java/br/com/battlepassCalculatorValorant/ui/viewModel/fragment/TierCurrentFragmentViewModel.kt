package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TierCurrentFragmentViewModel : ViewModel() {

    private val _tierName: MutableLiveData<String> = MutableLiveData<String>("Esquema Stinger")
    private val _percentageTier: MutableLiveData<Float> = MutableLiveData<Float>(53.17F)
    private val _imagesURL: MutableLiveData<List<String>> = MutableLiveData<List<String>>(
        listOf(
            "https://image.flaticon.com/icons/png/512/1384/1384060.png",
            "https://image.flaticon.com/icons/png/512/1384/1384060.png"
        )
    )


    val tierName: LiveData<String> = _tierName
    val percentageTier: LiveData<Float> = _percentageTier
    val imagesURL: LiveData<List<String>> = _imagesURL

//    val tierIndex = requireContext().getString(R.string.tier) + " " + tier.index.toString()
//    val tierReward = tier.reward[0].nome
//    val percentageTier = properties.percentageTier()
//    val percentageTierText =
//        percentageTier.toString() + "% " + requireContext().getString(R.string.feito)
//
//    tv_tier_index.text = tierIndex
//    tv_tier_rewind.text = tierReward
//    tv_percentage_tier.text = percentageTierText
//
//    val mViewPagerAdapter = MyImageSliderAdapter(requireContext(), tier.reward[0])
//    viewPagerMain.adapter = mViewPagerAdapter
//    dots_indicator_images.setViewPager2(viewPagerMain)

}