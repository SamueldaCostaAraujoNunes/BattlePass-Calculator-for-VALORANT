package br.com.battlepassCalculatorValorant.model.Util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


class DowloaderImagers(val context: Context, private val service: DowloaderImagersService) {

    companion object {
        val glide: GlideService
            get() {
                return GlideService()
            }
    }

    fun downloadImage(uri: String, imageView: ImageView) {
        service.downloadImage(context, uri, imageView)
    }

}

interface DowloaderImagersService {
    fun downloadImage(context: Context, uri: String, imageView: ImageView)
}

class GlideService : DowloaderImagersService {
    override fun downloadImage(context: Context, uri: String, imageView: ImageView) {
        Glide.with(context).load(uri).into(imageView)
    }
}
