package com.example.valorantpassbattle.model.Properties

import android.util.Log
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.Tier

class Properties(val historic: Historic, val passBattle: PassBattle) {

    fun historicTierPositionPerXp(): ArrayList<Int> {
        val mHistoric = ArrayList(historic)
        mHistoric.sortBy { it.tierCurrent }
        val tiersPerXp = ArrayList<Int>()
        var ultimoTier = 0
        var ultimoXp = 0F

        if(!mHistoric.isEmpty()){
            tiersPerXp.add(0)
            for(tierUserInput in mHistoric){
                val tierCurrent = passBattle.getTier(tierUserInput.tierCurrent)!!
                val expCurrent = (tierCurrent.expInitial + (tierCurrent.expMissing - tierUserInput.tierExpMissing)).toFloat()

                val diferencaDeTier = tierCurrent.index - ultimoTier
                val diferencaDeXp = expCurrent - ultimoXp
                val razao = diferencaDeXp / diferencaDeTier

                for (t in 1..diferencaDeTier){
                    Log.i("Historic", (t + ultimoTier).toString())
                    val xp = ((razao*t) + ultimoXp).toInt()
                    tiersPerXp.add(xp)
                }
                ultimoTier = tierCurrent.index
                ultimoXp = expCurrent
            }
        }
        return tiersPerXp
    }

    fun getListTiers(): ArrayList<Tier> {
        return passBattle.tiers
    }

    fun getTierCurrent(): Int {
        val ultimoTier: Int = if (historic.isEmpty()) 0 else historic.last().tierCurrent
        return ultimoTier
    }
}