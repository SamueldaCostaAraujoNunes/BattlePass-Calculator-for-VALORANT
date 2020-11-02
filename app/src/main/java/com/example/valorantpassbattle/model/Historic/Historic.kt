package com.example.valorantpassbattle.model.Historic

class Historic() {
    val arrayXp: ArrayList<UserInputsXp> = ArrayList<UserInputsXp>()

    fun add(userInput: UserInputsXp){
        arrayXp.add(userInput)
    }

    fun generateHistoricXpUser(): ArrayList<Int> {
        val xpPerTier = ArrayList<Int>()
        val arrayComparcao = ArrayList<UserInputsXp>(arrayXp)
        arrayComparcao.sortWith(compareBy { it -> it.tierCurrent })
        var ultimoTierListado = 0
        for(tier in arrayComparcao){
            val rangeTiers = ArrayList<Int>()
            val interval = ultimoTierListado..tier.tierCurrent
            val q = tier.tierCurrent - ultimoTierListado
            for (inicio in interval){
                var razao = if(q != 0) {
                    tier.xpAtual / (tier.tierCurrent - ultimoTierListado)
                }else{
                    tier.xpAtual
                }
                rangeTiers.add((razao*inicio).toInt())
            }
            xpPerTier.addAll(rangeTiers)
            ultimoTierListado = tier.tierCurrent
        }
        return xpPerTier
    }

    fun generateMedianProgress(): ArrayList<Int> {
        val xpPerTier = ArrayList<Int>()
        val arrayComparcao = ArrayList<UserInputsXp>(arrayXp)
        arrayComparcao.sortWith(compareBy { it -> it.tierCurrent })
        val ultimoTier = arrayComparcao.last()
        val xpMedio = ultimoTier.xpAtual / ultimoTier.tierCurrent
        for(tier in 0..ultimoTier.tierCurrent){
            xpPerTier.add((xpMedio * tier).toInt())
        }
        return xpPerTier
    }
}
