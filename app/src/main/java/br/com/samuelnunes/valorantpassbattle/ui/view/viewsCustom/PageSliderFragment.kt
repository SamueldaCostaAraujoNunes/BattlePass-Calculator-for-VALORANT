package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentPagerSliderBinding
import br.com.samuelnunes.valorantpassbattle.extensions.setAdapterSlider
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.FragmentSliderAdapter
import timber.log.Timber

abstract class PagerSliderFragment(
    private var fragments: List<FragmentWithTitle> = ArrayList(),
    private var adaptiveHeight: Boolean
) : Fragment() {
    private lateinit var binding: FragmentPagerSliderBinding
    private lateinit var adapter: FragmentSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerSliderBinding.inflate(inflater, container, false)
        adapter = FragmentSliderAdapter(this)
        adapter.addFragments(fragments)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.pager.setAdapterSlider(adapter, adaptiveHeight)
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                try {
                    val item = adapter.getItem(position)
                    binding.cardModule.title(getString(item.titleResId))
                    val v: View? = item.view
                    if (adaptiveHeight && v != null) {
                        changeDimensLayoutAnimation(v, binding.pager)
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }

            }

        })
        binding.dotsIndicator.setViewPager2(binding.pager)
        binding.dotsIndicator.dotsClickable = false
        return binding.root
    }

    private fun changeDimensLayoutAnimation(v: View, parent: ViewPager2) {
        v.post {
            val wMeasureSpec =
                View.MeasureSpec.makeMeasureSpec(v.width, View.MeasureSpec.EXACTLY)
            val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            v.measure(wMeasureSpec, hMeasureSpec)
            try {
                val anim = ValueAnimator.ofInt(parent.layoutParams.height, v.measuredHeight)
                anim.addUpdateListener {
                    val height = it.animatedValue as Int
                    parent.layoutParams.height = height
                }
                anim.duration = 210
                anim.start()
            } catch (e: IllegalStateException) {
                parent.layoutParams = (parent.layoutParams as ViewGroup.LayoutParams)
                    .also { lp -> lp.height = v.measuredHeight }
            }
        }
    }
}

abstract class FragmentWithTitle : Fragment() {
    abstract val titleResId: Int
}