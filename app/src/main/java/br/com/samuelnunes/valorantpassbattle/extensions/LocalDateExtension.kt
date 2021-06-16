package br.com.samuelnunes.valorantpassbattle.extensions

import android.content.Context
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LocalDate.formatterLocale(
    context: Context
): String {
    val zdt: ZonedDateTime = atStartOfDay(ZoneOffset.UTC)
    val pattern =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(context.locale)
    return zdt.format(pattern)
}