package br.com.battlepassCalculatorValorant.model.Historic

import java.util.*

class UserInputsTier(val tierCurrent: Int, val tierExpMissing: Int) {

    constructor(tierCurrent: Int, tierExpMissing: Int, dateNow: Calendar) : this(
        tierCurrent,
        tierExpMissing
    ) {
        date = dateNow
    }

    var date: Calendar = Calendar.getInstance()
}
