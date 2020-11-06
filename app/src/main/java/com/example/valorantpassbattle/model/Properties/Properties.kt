package com.example.valorantpassbattle.model.Properties

import android.util.Log
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.PassBattle.PassBattle

class Properties(val historic: Historic, val passBattle: PassBattle) {

    fun historicTierPositionPerXp(): ArrayList<Int> {
        val mHistoric = ArrayList(historic)
        mHistoric.sortBy { it.tierCurrent }
        val tiersPerXp = ArrayList<Int>()
        var tierInicial = 0
        var xpTotal = 0
        var ultimoXp = 0
        for(tier in mHistoric){
            val tierCurrent = passBattle.getTier(tier.tierCurrent)!!
            Log.i("Historic", "index: " + tierCurrent.index)
            val xpSum = (tierCurrent.expInitial + (tierCurrent.expMissing - tier.tierExpMissing)).toFloat()
            Log.i("Historic", "xpSum: " + xpSum)
            val razao = (xpSum - xpTotal) / (tier.tierCurrent - tierInicial)
            Log.i("Historic", "razao: " + razao)
            for(t in tierInicial .. tier.tierCurrent){
                Log.i("Historic", "ultimoXp: " + ultimoXp)
                tiersPerXp.add(ultimoXp + razao.toInt())
                ultimoXp = (t*razao).toInt()
            }
            tierInicial = tier.tierCurrent
            xpTotal = xpSum.toInt()
        }
        return tiersPerXp
    }
}