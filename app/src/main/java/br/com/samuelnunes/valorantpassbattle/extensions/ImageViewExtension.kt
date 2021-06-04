package br.com.samuelnunes.valorantpassbattle.extensions

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("imageUrl")
fun ImageView.loadImageURL(url: String?) {
    if (url != null) {
        val drawableTransitionOptions = DrawableTransitionOptions().crossFade(
            DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        )
        Glide.with(context).load(url)
            .placeholder(ColorDrawable(Color.TRANSPARENT))
            .error(R.drawable.img_error)
            .transition(drawableTransitionOptions)
            .into(this)
    }
}

