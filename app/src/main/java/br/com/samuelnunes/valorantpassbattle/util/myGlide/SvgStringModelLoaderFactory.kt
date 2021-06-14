package br.com.samuelnunes.valorantpassbattle.util.myGlide

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class SvgStringModelLoaderFactory : ModelLoaderFactory<String, InputStream> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
        return object : ModelLoader<String, InputStream> {
            override fun handles(model: String) = model.contains("<svg")

            override fun buildLoadData(
                model: String,
                width: Int,
                height: Int,
                options: Options
            ): ModelLoader.LoadData<InputStream>? {
                return ModelLoader.LoadData<InputStream>(
                    Key { messageDigest -> messageDigest.update("svg_string_$model".toByteArray()) },
                    object : DataFetcher<InputStream> {
                        override fun cancel() {}

                        override fun getDataSource() = DataSource.LOCAL

                        override fun loadData(
                            priority: Priority,
                            callback: DataFetcher.DataCallback<in InputStream>
                        ) {
                            callback.onDataReady(model.byteInputStream())
                        }

                        override fun getDataClass() = InputStream::class.java

                        override fun cleanup() {}
                    })
            }
        }
    }

    override fun teardown() {

    }
}