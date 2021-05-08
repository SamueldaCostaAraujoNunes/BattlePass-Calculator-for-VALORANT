package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.databinding.ItemImageViewBinding


class ImageSliderAdapter(private var imagesUrl: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.PageHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageHolder {
        return PageHolder(
            ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = imagesUrl.size

    override fun onBindViewHolder(
        holder: PageHolder,
        position: Int
    ) {
        holder.binding.imageURL = imagesUrl[position]
    }

    inner class PageHolder(val binding: ItemImageViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
