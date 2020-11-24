package br.com.battlepassCalculatorValorant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.PassBattle.Tier
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image_view.view.*


internal class MyImageSliderAdapter(// Context object
    var context: Context, // Array of images
    var tier: Tier

) : RecyclerView.Adapter<MyImageSliderAdapter.PageHolder>() {

    val reward = tier.reward[0]
    var imagesUrl = selectedImageUrl()

    fun selectedImageUrl(): ArrayList<String> {
        return if (reward.isCard()) {
            arrayListOf(reward.imagens[1])
        } else {
            reward.imagens
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_view, parent, false)
        return PageHolder(view)
    }

    override fun getItemCount(): Int = imagesUrl.size

    override fun onBindViewHolder(
        holder: PageHolder,
        position: Int
    ) {
        Picasso.with(context).load(imagesUrl[position]).into(holder.imgView)
    }

    class PageHolder(root: View) : RecyclerView.ViewHolder(root) {
        val imgView = root.imageViewMain
    }
}
