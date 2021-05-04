package br.com.battlepassCalculatorValorant.model.Singleton

import android.content.Context
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePassFactory

class ManagerPassBattle {
    companion object : SingletonHolder<BattlePassFactory, Context>(::BattlePassFactory)
}

