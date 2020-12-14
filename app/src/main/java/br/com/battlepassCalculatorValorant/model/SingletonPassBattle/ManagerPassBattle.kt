package br.com.battlepassCalculatorValorant.model.SingletonPassBattle

import android.content.Context
import br.com.battlepassCalculatorValorant.model.PassBattle.PassBattleFactory

class ManagerPassBattle {
    companion object : SingletonHolder<PassBattleFactory, Context>(::PassBattleFactory)
}

