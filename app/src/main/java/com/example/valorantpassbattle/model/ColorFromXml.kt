package com.example.valorantpassbattle.model

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.valorantpassbattle.R

class ColorFromXml(val context: Context) {

    fun getColor(colorId: Int): String {
        return "#" + Integer.toHexString(
            ContextCompat.getColor(
                context,
                colorId
            )).substring(2)
    }

}