package br.com.battlepassCalculatorValorant.model.ListListener

import android.content.Context
import br.com.battlepassCalculatorValorant.model.DataBase.DbTierInput
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier

open class HistoricArrayListListener(context: Context) : ArrayListListener<UserInputsTier>() {

    private val db: DbTierInput = DbTierInput(context)

    init {
        addAll(db.readData())
    }

    fun create(userInputTier: UserInputsTier): Boolean {
        return if (getToId(userInputTier.tierCurrent) != null) {
            update(userInputTier)
        } else {
            add(userInputTier) && db.insertData(userInputTier)
        }
    }

    fun read(id: Int): UserInputsTier? {
        val index = getIndexToId(id)
        return if (index >= 0) this[index] else null
    }

    fun update(userInputTier: UserInputsTier): Boolean {
        val index = getIndexToId(userInputTier.tierCurrent)
        val local = try {
            set(index, userInputTier)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        }
        val extern = db.updateData(userInputTier)
        return extern && local
    }

    fun delete(id: Int): Boolean {
        val element = getToId(id)
        if (element != null) {
            val local = remove(element)
            val extern = db.deleteData(element.tierCurrent.toString())
            return local && extern
        }
        return true
    }

    override fun clear() {
        super.clear()
        db.removeAll()
    }

    private fun getToId(id: Int): UserInputsTier? {
        for (userInputsTier: UserInputsTier in this) {
            if (userInputsTier.tierCurrent == id) {
                return userInputsTier
            }
        }
        return null
    }

    private fun getIndexToId(id: Int): Int {
        var aux = 0
        for (userInputsTier: UserInputsTier in this) {
            if (userInputsTier.tierCurrent == id) {
                return aux
            }
            aux += 1
        }
        return -1
    }
}