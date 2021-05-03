package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.battlepassCalculatorValorant.R
import com.bumptech.glide.Glide

//fun ImageView.loadImage(url: String?) {
//    if (url != null) {
//        Glide.with(context).load(url).into(this)
//    }
//}

@BindingAdapter("imageUrl")
fun ImageView.loadImageURL(url: String?) {
    if (url != null) {
        Glide.with(context).load(url).error(R.drawable.error).into(this)
    }
}

