package br.com.battlepassCalculatorValorant.extensions

import java.util.*

fun Calendar.daysApart(other: Calendar): Int {
    var days = this[Calendar.DAY_OF_YEAR] - other[Calendar.DAY_OF_YEAR]
    val d1p = Calendar.getInstance()
    d1p.time = other.time
    while (d1p[Calendar.YEAR] < this[Calendar.YEAR]) {
        days += d1p.getActualMaximum(Calendar.DAY_OF_YEAR)
        d1p.add(Calendar.YEAR, 1)
    }
    return days
}