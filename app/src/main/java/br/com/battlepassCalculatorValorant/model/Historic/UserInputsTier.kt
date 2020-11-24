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
    private var mId: Int? = null
    var id: Int
        get() = mId ?: -1
        set(value) {
            mId = value
        }

    override fun equals(other: Any?): Boolean {
        val outro = other as UserInputsTier
        return this.id == outro.id
    }
}
