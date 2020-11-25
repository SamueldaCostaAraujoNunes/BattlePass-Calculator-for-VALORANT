package br.com.battlepassCalculatorValorant.model.Historic

import android.content.Context
import br.com.battlepassCalculatorValorant.model.ListListener.HistoricArrayListListener
import br.com.battlepassCalculatorValorant.model.Observer.IObservable
import br.com.battlepassCalculatorValorant.model.Observer.IObserver

class Historic(context: Context) : HistoricArrayListListener(context), IObservable {
    override val observers: ArrayList<IObserver> = ArrayList()

    init {
        onChangeListener = {
            sortBy { it.tierCurrent }
            sendUpdateEvent()
        }
    }
}
