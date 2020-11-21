package br.com.battlepassCalculatorValorant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class InfosFragment : Fragment() {

    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.Companion.properties
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criaAnuncio(view, R.id.adView4)
        criaAnuncio(view, R.id.adView5)
    }

    fun criaAnuncio(view: View, id: Int) {
        val mAdView = view.findViewById<AdView>(id)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        properties.historic.deleteAll()
    }
}