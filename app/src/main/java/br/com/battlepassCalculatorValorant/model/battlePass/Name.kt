package br.com.battlepassCalculatorValorant.model.battlePass

import org.json.JSONObject
import java.util.*

class Name {
    companion object {
        val languagesSuported = arrayOf("pt", "es")
    }

    fun translate(json: JSONObject): String {
        var language = Locale.getDefault().language

        language = if (languagesSuported.contains(language)) {
            language
        } else {
            "default"
        }

        return json.get(language).toString()
    }
}