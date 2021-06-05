package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.valorantpassbattle.databinding.ItemImageViewBinding


class ImageSliderAdapter :
    ListAdapter<String, ImageSliderAdapter.ImageSliderViewHolder>(ImageSliderDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageSliderViewHolder {
        return ImageSliderViewHolder(
            ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ImageSliderViewHolder,
        position: Int
    ) {
        holder.binding.imageURL = currentList[position]
    }

    inner class ImageSliderViewHolder(val binding: ItemImageViewBinding) :
        RecyclerView.ViewHolder(binding.root)

}

class ImageSliderDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}