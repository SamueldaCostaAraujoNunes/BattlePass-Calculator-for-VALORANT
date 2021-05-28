package br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.R
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun ImageView.loadImageURL(url: String?) {
    if (url != null) {
        Glide.with(context).load(url).error(R.drawable.img_error).into(this)
    }
}

