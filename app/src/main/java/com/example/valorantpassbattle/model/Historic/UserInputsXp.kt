package com.example.valorantpassbattle.model.Historic

import android.util.Log
import com.example.valorantpassbattle.model.PassBattle.PassBattle

class UserInputsXp(val tierCurrent: Int, val tierExpMissing: Int, passBattle: PassBattle) {
    var xpAtual:Float = 0F
    var porcentage: Float = 0F
    init {
        for (chapter in passBattle.chapters){
            for(tier in chapter.tiers){
                if(tier.index == tierCurrent){
                    xpAtual = (tier.expInitial + tier.expMissing - tierExpMissing).toFloat()

                }
            }
        }
        porcentage = xpAtual / passBattle.expTotal
        porcentage = (Math.round(porcentage*100.0)/100.0).toFloat()
    }
}
