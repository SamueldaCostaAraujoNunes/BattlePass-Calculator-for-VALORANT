package br.com.battlepassCalculatorValorant.ui.fragment.Propriedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import kotlinx.android.synthetic.main.fragment_ad_mob.*

class AdMobFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ad_mob, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adContainer8.createBanner(R.string.admob_card8, Advertisement.MEDIUM_RECTANGLE)
    }

}