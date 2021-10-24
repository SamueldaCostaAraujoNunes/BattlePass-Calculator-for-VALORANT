package br.com.samuelnunes.valorantpassbattle.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import br.com.samuelnunes.valorantpassbattle.model.GameType.GameType
import com.google.android.material.tabs.TabLayout

fun TabLayout.addTabItem(position: Int, @DrawableRes iconRes: Int,  @StringRes textRes: Int){
    val tab = newTab().apply {
        icon = ContextCompat.getDrawable(context, iconRes)
        text = resources.getText(textRes)
    }
    addTab(tab, position)
}

fun TabLayout.addGameTypeItem(gameType: GameType){
    addTabItem(gameType.ordinal, gameType.iconRes, gameType.textRes)
}
