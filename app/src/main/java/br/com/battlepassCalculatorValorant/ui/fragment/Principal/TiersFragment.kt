package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ColorFromXml
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import br.com.battlepassCalculatorValorant.ui.adapter.MyItemRecyclerViewAdapter


class TiersFragment : Fragment() {

    private var columnCount = 1
    private var tierCurrent = 1
    lateinit var properties: Properties
    lateinit var colorXML: ColorFromXml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.Companion.properties
        colorXML = MainActivity.Companion.colorXML
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tiers_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(
                    values = properties.getListTiers(),
                    colorXML = colorXML,
                    tierCurrentFun = properties::getTierCurrent
                )
                properties.historic.add(adapter as MyItemRecyclerViewAdapter)
                tierCurrent = if(properties.historic.isEmpty()) 1 else properties.historic.last().tierCurrent
            }
        }
        (view as RecyclerView).layoutManager?.scrollToPosition(tierCurrent - 5)
        return view
    }
}