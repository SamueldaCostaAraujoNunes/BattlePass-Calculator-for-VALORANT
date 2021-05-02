package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyImageSliderAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

@BindingAdapter("imagesURl", "indicator")
fun ViewPager2.imagesURl(imagesURl: List<String>, indicator: DotsIndicator?) {
    adapter = MyImageSliderAdapter(this.context, imagesURl)
    indicator?.setViewPager2(this)
}
