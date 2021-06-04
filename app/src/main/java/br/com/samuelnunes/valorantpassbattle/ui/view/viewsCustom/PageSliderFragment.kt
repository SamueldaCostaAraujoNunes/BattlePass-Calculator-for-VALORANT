package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentPagerSliderBinding
import br.com.samuelnunes.valorantpassbattle.extensions.setAdapterSlider
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.FragmentSliderAdapter

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
        binding.dotsIndicator.setViewPager2(binding.pager)
//        binding.dotsIndicator.dotsClickable = false
        return binding.root
    }
}

abstract class FragmentWithTitle : Fragment() {
    protected val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> = _title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _title.postValue(getString(titleResId))
    }

    abstract val titleResId: Int
}