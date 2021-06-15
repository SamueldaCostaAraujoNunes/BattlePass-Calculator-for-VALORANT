package br.com.samuelnunes.valorantpassbattle.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.util.myGlide.GlideApp
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


@BindingAdapter("imageUrl")
fun ImageView.loadImageURL(url: String?) {
    if (url != null) {
        val gsReference: StorageReference = Firebase.storage.getReference(url)
        val drawableTransitionOptions = DrawableTransitionOptions().crossFade(
            DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        )
        GlideApp.with(context).load(gsReference)
            .placeholder(ColorDrawable(Color.TRANSPARENT))
            .error(R.drawable.img_error)
            .transition(drawableTransitionOptions)
            .fitCenter()
            .into(this)

    }
}
