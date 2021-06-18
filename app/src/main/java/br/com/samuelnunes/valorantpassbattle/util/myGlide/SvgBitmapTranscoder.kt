package br.com.samuelnunes.valorantpassbattle.util.myGlide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG
import timber.log.Timber

class SvgBitmapTranscoder : ResourceTranscoder<SVG, Bitmap> {
    override fun transcode(toTranscode: Resource<SVG>, options: Options): Resource<Bitmap> {
        val svg = toTranscode.get()

        val width: Int
        val height: Int

        Timber.d("documentWidth: ${svg.documentWidth.toInt()}")
        if (svg.documentViewBox.right.toInt() > 700) {
            width = svg.documentViewBox.right.toInt()
            height = svg.documentViewBox.bottom.toInt()
            svg.documentHeight = svg.documentViewBox.bottom
            svg.documentWidth = svg.documentViewBox.right
        } else {
            width = svg.documentWidth.toInt().takeIf { it > 0 }
                ?: (svg.documentViewBox.right - svg.documentViewBox.left).toInt()
            height = svg.documentHeight.toInt().takeIf { it > 0 }
                ?: (svg.documentViewBox.bottom - svg.documentViewBox.top).toInt()
        }

        Timber.d("width: $width")
        Timber.d("heigth: $height")

        val picture = svg.renderToPicture(width, height)
        val drawable = PictureDrawable(picture)

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawPicture(drawable.picture)

        return SimpleResource(bitmap)
    }
}