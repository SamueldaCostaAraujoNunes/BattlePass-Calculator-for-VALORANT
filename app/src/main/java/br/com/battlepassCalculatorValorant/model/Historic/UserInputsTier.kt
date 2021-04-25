package br.com.battlepassCalculatorValorant.model.Historic

import java.util.*

class UserInputsTier(val tierCurrent: Int, var tierExpMissing: Int) {

    var date: Calendar = Calendar.getInstance()

    constructor(tierCurrent: Int, tierExpMissing: Int, dateNow: Calendar) : this(
        tierCurrent,
        tierExpMissing
    ) {
        date = dateNow
    }

}
