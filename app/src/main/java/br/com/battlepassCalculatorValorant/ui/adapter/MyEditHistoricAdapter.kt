package br.com.battlepassCalculatorValorant.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import br.com.battlepassCalculatorValorant.ui.dialog.DialogConfimation
import br.com.battlepassCalculatorValorant.ui.dialog.DialogEditInput

class MyEditHistoricAdapter(context: Context, val historic: Historic) :
    ArrayAdapter<UserInputsTier>(context, 0, historic) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listLayout = convertView
        val holder: ViewHolder

        if (listLayout == null) {
            val inflateList = (context as Activity).layoutInflater
            listLayout = inflateList.inflate(R.layout.item_edit_historic, parent, false)
            holder = ViewHolder()

            holder.textViewTier = listLayout!!.findViewById(R.id.tv_exp_initial_tier)
            holder.textViewExpMissing = listLayout.findViewById(R.id.tv_exp_missing_tier)
            holder.imageButtonEdit = listLayout.findViewById(R.id.btn_edit)
            holder.imageButtonDelete = listLayout.findViewById(R.id.btn_delete)

            listLayout.tag = holder
        } else {
            holder = listLayout.tag as ViewHolder
        }
        val listItem = historic[position]
        holder.textViewTier!!.text = listItem.tierCurrent.toString()
        holder.textViewExpMissing!!.text = listItem.tierExpMissing.toString()

        holder.imageButtonDelete!!.setOnClickListener {
            DialogConfimation(context, position, historic).show { notifyDataSetChanged() }
        }

        holder.imageButtonEdit!!.setOnClickListener {
            DialogEditInput(context, position, historic).show()
            notifyDataSetChanged()
        }
        return listLayout
    }

    class ViewHolder {
        internal var textViewTier: TextView? = null
        internal var textViewExpMissing: TextView? = null
        internal var imageButtonEdit: ImageButton? = null
        internal var imageButtonDelete: ImageButton? = null

    }
}
