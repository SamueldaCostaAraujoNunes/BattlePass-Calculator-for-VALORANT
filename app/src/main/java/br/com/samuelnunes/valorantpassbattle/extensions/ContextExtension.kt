package br.com.samuelnunes.valorantpassbattle.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import java.util.*

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun Context.getColorHexFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): String {
    return String.format("#%06X", 0xFFFFFF and getColorFromAttr(attrColor, typedValue, resolveRefs))
}

val Context.locale: Locale
    get() = resources.configuration.locales[0]

fun Context.copyToClipboard(text: CharSequence) {
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    clipboard?.setPrimaryClip(ClipData.newPlainText("", text))
}