package com.example.valorantpassbattle.model.Historic

import android.content.Context
import com.example.valorantpassbattle.model.DataBase.MySharedPreferences
import com.example.valorantpassbattle.model.Observer.IObservable
import com.example.valorantpassbattle.model.Observer.IObserver
import java.util.Comparator
import java.util.function.Predicate

class Historic(context: Context): ArrayList<UserInputsTier>(), IObservable {
    private val bd = MySharedPreferences(context)
    private val mObserver = ArrayList<IObserver>()
        init {
        val loadArray: List<UserInputsTier> = bd.getListHistoric()
        this.addAll(loadArray)
    }

    // ArrayList

    override fun set(index: Int, element: UserInputsTier): UserInputsTier {
        val aux = super.set(index, element)
        event()
        return aux
    }

    override fun remove(element: UserInputsTier): Boolean {
        val aux = super<ArrayList>.remove(element)
        event()
        return aux
    }

    override fun removeAll(elements: Collection<UserInputsTier>): Boolean {
        val aux = super.removeAll(elements)
        event()
        return aux
    }

    override fun removeIf(filter: Predicate<in UserInputsTier>): Boolean {
        val aux = super.removeIf(filter)
        event()
        return aux
    }

    override fun removeAt(index: Int): UserInputsTier {
        val aux = super.removeAt(index)
        event()
        return aux
    }

    override fun add(element: UserInputsTier): Boolean {
        val aux = super<ArrayList>.add(element)
        event()
        return aux
    }

    override fun add(index: Int, element: UserInputsTier) {
        super<ArrayList>.add(index, element)
        event()
    }

    override fun addAll(elements: Collection<UserInputsTier>): Boolean {
        val aux = super.addAll(elements)
        event()
        return aux
    }

    override fun addAll(index: Int, elements: Collection<UserInputsTier>): Boolean {
        val aux = super.addAll(index, elements)
        event()
        return aux
    }

    override fun clear() {
        super.clear()
        event()
    }

    // Observer

    private fun event(){
        sortBy { it.tierCurrent }
        bd.setListHistoric(this)
        sendUpdateEvent()
    }

    override val observers: ArrayList<IObserver>
        get() = mObserver

    override fun add(observer: IObserver) {
        observers.add(observer)
    }

    override fun remove(observer: IObserver) {
        observers.remove(observer)
    }

    override fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }
}
