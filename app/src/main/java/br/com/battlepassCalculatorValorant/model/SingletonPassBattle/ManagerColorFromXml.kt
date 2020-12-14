package br.com.battlepassCalculatorValorant.model.SingletonPassBattle

import android.content.Context
import br.com.battlepassCalculatorValorant.model.ColorFromXml

class ManagerColorFromXml {
    companion object : SingletonHolder<ColorFromXml, Context>(::ColorFromXml)
}
