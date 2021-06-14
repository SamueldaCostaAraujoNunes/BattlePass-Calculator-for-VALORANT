package br.com.samuelnunes.valorantpassbattle.repository

import br.com.samuelnunes.valorantpassbattle.model.BattlePassManager
import br.com.samuelnunes.valorantpassbattle.model.dto.FormQuestion
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import javax.inject.Inject

const val FORM_URL =
    "https://docs.google.com/forms/u/0/d/e/1FAIpQLSfyjyWz9ZPGB99Zrtge4rv_8ZSMBM7EU1ShabdYfozgOw7WKQ/formResponse"

class FormsRepository @Inject constructor(
    private val client: OkHttpClient,
    val battlePassManager: BattlePassManager
) {

    fun submitAnswers(data: List<FormQuestion>) {
        val thread = Thread {
            try {
                submitAnswer(data)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        thread.start()
    }

    private fun submitAnswer(data: List<FormQuestion>) {

        val form = FormBody.Builder().apply {
            data.forEach { addEncoded(it.idForm, it.answer) }
        }.build()

        val request = Request.Builder()
            .url(FORM_URL)
            .post(form)
            .build()

        val call = client.newCall(request)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                Timber.d("Answers submitted!")
            } else {
                Timber.e("Failed to answer survey due to error ${response.code}")
            }
        } catch (error: Throwable) {
            Timber.e(error, "Failed to perform call")
        }
    }
}
