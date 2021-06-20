package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import br.com.samuelnunes.valorantpassbattle.R

class PreferenceSpace @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Preference(context, attrs, defStyleAttr) {
    init {
        widgetLayoutResource = R.layout.preference_space
    }
}