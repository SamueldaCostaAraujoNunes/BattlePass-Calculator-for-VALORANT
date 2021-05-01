package br.com.battlepassCalculatorValorant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.BattlePass.Reward
import br.com.battlepassCalculatorValorant.model.Util.DowloaderImagers
import kotlinx.android.synthetic.main.item_image_view.view.*


internal class MyImageSliderAdapter(// Context object
    var context: Context, // Array of images
    var reward: Reward

) : RecyclerView.Adapter<MyImageSliderAdapter.PageHolder>() {

    private var imagesUrl = selectedImageUrl()

    private fun selectedImageUrl(): ArrayList<String> {
        return reward.imagens
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

        val uri = imagesUrl[position]
        val imageView = holder.imgView
        DowloaderImagers(context, DowloaderImagers.glide).downloadImage(
            uri,
            imageView
        )
    }

    class PageHolder(root: View) : RecyclerView.ViewHolder(root) {
        val imgView = root.imageViewMain
    }
}
