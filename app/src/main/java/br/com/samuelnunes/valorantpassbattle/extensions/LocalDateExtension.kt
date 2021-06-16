package br.com.samuelnunes.valorantpassbattle.extensions

import android.content.Context
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LocalDate.formatterLocale(
    context: Context,
    formatStyle: FormatStyle
): String {
    val zdt: ZonedDateTime = atStartOfDay(ZoneOffset.UTC)
    val pattern =
        DateTimeFormatter.ofLocalizedDate(formatStyle).withLocale(context.locale)
    return zdt.format(pattern)
}