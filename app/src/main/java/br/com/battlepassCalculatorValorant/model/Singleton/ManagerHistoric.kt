package br.com.battlepassCalculatorValorant.model.Singleton

import android.content.Context
import br.com.battlepassCalculatorValorant.model.Historic.Historic

class ManagerHistoric {
    companion object : SingletonHolder<Historic, Context>(::Historic)
}

