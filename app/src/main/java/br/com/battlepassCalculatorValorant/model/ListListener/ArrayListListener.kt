package br.com.battlepassCalculatorValorant.model.ListListener

import java.util.function.Predicate

open class ArrayListListener<T> : ArrayList<T>() {

    var onChangeListener: () -> Unit = {}

    override fun set(index: Int, element: T): T {
        val aux = super.set(index, element)
        onChangeListener()
        return aux
    }

    override fun remove(element: T): Boolean {
        val aux = super.remove(element)
        onChangeListener()
        return aux
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val aux = super.removeAll(elements)
        onChangeListener()
        return aux
    }

    override fun removeIf(filter: Predicate<in T>): Boolean {
        val aux = super.removeIf(filter)
        onChangeListener()
        return aux
    }

    override fun removeAt(index: Int): T {
        val aux = super.removeAt(index)
        onChangeListener()
        return aux
    }

    override fun add(element: T): Boolean {
        val aux = super.add(element)
        onChangeListener()
        return aux
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        onChangeListener()
    }

    final override fun addAll(elements: Collection<T>): Boolean {
        val aux = super.addAll(elements)
        onChangeListener()
        return aux
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val aux = super.addAll(index, elements)
        onChangeListener()
        return aux
    }

    override fun clear() {
        super.clear()
        onChangeListener()
    }
}

