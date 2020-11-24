package br.com.battlepassCalculatorValorant.model.Historic

import android.content.Context
import br.com.battlepassCalculatorValorant.model.DataBase.DbTierInput
import br.com.battlepassCalculatorValorant.model.ListListener.ArrayListListener
import br.com.battlepassCalculatorValorant.model.Observer.IObservable
import br.com.battlepassCalculatorValorant.model.Observer.IObserver

class Historic(context: Context) : ArrayListListener<UserInputsTier>(), IObservable {
    private val db = DbTierInput(context)
    override val observers: ArrayList<IObserver> = ArrayList()

    init {
        val loadArray: List<UserInputsTier> = db.readData()
        addAll(loadArray)
        onChangeListener = {
            sortBy { it.tierCurrent }
            saveInDb(db)
            sendUpdateEvent()
        }
    }

    override fun add(element: UserInputsTier): Boolean {
        for ((cont, inputUser) in this.withIndex()) {
            if (element.tierCurrent == inputUser.tierCurrent) {
                super.set(cont, element)
                onChangeListener()
                return true
            }
        }
        val aux = super<ArrayListListener>.add(element)
        onChangeListener()
        return aux
    }

    fun saveInDb(db: DbTierInput) {
        val listDbCurrent = db.readData()
        if (this.isEmpty()) {
            db.removeAll()
        } else {
            for (userInput in this) {
                if (listDbCurrent.contains(userInput)) {
                    db.updateData(userInput.tierCurrent.toString(), userInput)
                } else {
                    db.insertData(userInput)
                }
            }
        }
    }

}
