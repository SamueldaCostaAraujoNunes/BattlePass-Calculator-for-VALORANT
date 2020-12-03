package br.com.battlepassCalculatorValorant.ui.Advertisement

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement.Companion.SMART_BANNER
import com.google.android.gms.ads.AdSize

class AdvertisementView(context: Context, attr: AttributeSet?, defStyleAttr: Int) : CardView(
    context,
    attr,
    defStyleAttr
) {
    constructor(context: Context) : this(
        context,
        null
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.style.CardViewStyle
    )

    private var advertisementGroup: RelativeLayout

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_advertisement, this, true)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        advertisementGroup = view.findViewById(R.id.advertisement)
    }

    fun createBanner(idAdMob: Int, size: AdSize = SMART_BANNER) {
        Advertisement(context).createBanner(advertisementGroup, idAdMob, size)
    }
}