package br.com.battlepassCalculatorValorant.ui.view.helpers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


@SuppressLint("ClickableViewAccessibility")
class ItemSwipeHelper(
    private val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private var swipedEventListener: SwipedEventListener? = null

    init {
        attachSwipe()
    }

    private fun attachSwipe() {
        val itemTouchHelper = ItemTouchHelper(this)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        swipedEventListener?.event(viewHolder, direction)
    }

    fun setOnSwipeListener(swipedEventListener: SwipedEventListener?) {
        this.swipedEventListener = swipedEventListener!!
    }
}

interface SwipedEventListener {
    fun event(viewHolder: RecyclerView.ViewHolder, direction: Int)
}