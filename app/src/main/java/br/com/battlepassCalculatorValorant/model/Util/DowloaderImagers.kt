package br.com.battlepassCalculatorValorant.model.Util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.sahasbhop.apngview.ApngDrawable
import com.github.sahasbhop.apngview.ApngImageLoader
import com.github.sahasbhop.apngview.ApngImageLoader.ApngConfig
import com.squareup.picasso.Picasso

class DowloaderImagers(val context: Context, private val service: DowloaderImagersService) {

    companion object {
        val picasso: PicassoService
            get() {
                return PicassoService()
            }
        val glide: GlideService
            get() {
                return GlideService()
            }
        val apng: APNGService
            get() {
                return APNGService()
            }
    }

    fun downloadImage(uri: String, imageView: ImageView) {
        service.downloadImage(context, uri, imageView)
    }

}

interface DowloaderImagersService {
    fun downloadImage(context: Context, uri: String, imageView: ImageView)
}

class PicassoService : DowloaderImagersService {
    override fun downloadImage(context: Context, uri: String, imageView: ImageView) {
        Picasso.with(context).load(uri).into(imageView)
    }
}

class GlideService : DowloaderImagersService {
    override fun downloadImage(context: Context, uri: String, imageView: ImageView) {
        Glide.with(context).load(uri).into(imageView)
    }

}

class APNGService : DowloaderImagersService {
    override fun downloadImage(context: Context, uri: String, imageView: ImageView) {
        val apng = ApngImageLoader.getInstance()
        apng.init(context)
        apng.displayApng(
            uri, imageView,
            ApngConfig(5, true)
        )
        imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val apngDrawable = ApngDrawable.getFromView(v) ?: return
                if (apngDrawable.isRunning) {
                    apngDrawable.stop()
                } else {
                    apngDrawable.numPlays = 5
                    apngDrawable.start()
                }
            }
        })
    }

}