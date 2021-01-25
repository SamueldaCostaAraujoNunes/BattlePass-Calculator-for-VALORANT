package br.com.battlepassCalculatorValorant.model.Singleton

import android.content.Context
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml

class ManagerColorFromXml {
    companion object : SingletonHolder<ColorFromXml, Context>(::ColorFromXml)
}
