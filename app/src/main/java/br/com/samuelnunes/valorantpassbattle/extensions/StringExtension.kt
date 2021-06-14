package br.com.samuelnunes.valorantpassbattle.extensions

import java.util.*

fun String.capitalize(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}