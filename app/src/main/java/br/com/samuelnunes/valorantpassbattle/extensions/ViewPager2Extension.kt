package br.com.samuelnunes.valorantpassbattle.extensions

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.FragmentSliderAdapter
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.ImageSliderAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

@BindingAdapter("imagesURl", "indicator")
fun ViewPager2.imagesURl(imagesURl: List<String>?, indicator: DotsIndicator?) {
    if (adapter == null) {
        adapter = ImageSliderAdapter()
    }
    if (imagesURl != null) {
        (adapter as ImageSliderAdapter).submitList(imagesURl)
        indicator?.setViewPager2(this)
    }
}

fun ViewPager2.setAdapterSlider(mAdapter: FragmentSliderAdapter, adaptiveHeight: Boolean) {
    adapter = mAdapter


}
