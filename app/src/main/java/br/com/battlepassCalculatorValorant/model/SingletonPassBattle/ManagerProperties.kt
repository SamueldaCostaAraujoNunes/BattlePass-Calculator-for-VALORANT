package br.com.battlepassCalculatorValorant.model.SingletonPassBattle

import android.content.Context
import br.com.battlepassCalculatorValorant.model.Properties.Properties

class ManagerProperties {
    companion object : SingletonHolder<Properties, Context>(::Properties)
}
