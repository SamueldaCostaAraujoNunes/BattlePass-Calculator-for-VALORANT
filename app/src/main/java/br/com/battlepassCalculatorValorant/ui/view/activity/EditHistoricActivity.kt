package br.com.battlepassCalculatorValorant.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.database.DialogEditInput
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyEditHistoricRecyclerViewAdapter
import br.com.battlepassCalculatorValorant.ui.view.adapter.RecyclerItemClickListener
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogDeleteItemConfimation
import br.com.battlepassCalculatorValorant.ui.view.helpers.HistoricItemSwipeHelper
import br.com.battlepassCalculatorValorant.ui.view.helpers.SwipedEventListener
import kotlinx.android.synthetic.main.activity_edit_historic.*


class EditHistoricActivity : AppCompatActivity() {

    val historic: Historic = ManagerProperties.getInstance(this).historic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_historic)

        val adapter = MyEditHistoricRecyclerViewAdapter(this, historic)
        rv_edit_historic.adapter = adapter

        // Event click item edit / delete
        rv_edit_historic.addOnItemTouchListener(
            RecyclerItemClickListener(
                this@EditHistoricActivity,
                rv_edit_historic,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        editItem(position, adapter)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        deleteItem(position, adapter)
                    }
                })
        )

        // Swipe delete item
        val swipe = HistoricItemSwipeHelper(rv_edit_historic)
        swipe.setOnSwipeListener(object : SwipedEventListener {
            override fun event(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val pos = viewHolder.adapterPosition
                deleteItem(pos, adapter)
            }
        })

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun editItem(
        pos: Int,
        adapter: MyEditHistoricRecyclerViewAdapter
    ) {
        DialogEditInput(this@EditHistoricActivity, pos, historic).show {
            adapter.notifyItemChanged(pos)
        }
    }

    private fun deleteItem(
        pos: Int,
        adapter: MyEditHistoricRecyclerViewAdapter
    ) {
        DialogDeleteItemConfimation(this@EditHistoricActivity, pos, historic).show(
            { adapter.notifyItemRemoved(pos) },
            { adapter.notifyItemChanged(pos) })
    }
}