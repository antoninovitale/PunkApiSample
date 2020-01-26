package com.ninovitale.punkapi.app.util.countdownview

import android.content.Context

/**
 * Utils
 * Created by iWgang on 16/6/19.
 * https://github.com/iwgang/CountdownView
 */
internal object Utils {
    fun dp2px(context: Context?, dpValue: Float): Int {
        if (dpValue <= 0) return 0
        val scale = context?.resources?.displayMetrics?.density ?: 1F
        return (dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(context: Context?, spValue: Float): Float {
        if (spValue <= 0) return 0F
        val scale = context?.resources?.displayMetrics?.scaledDensity ?: 1F
        return spValue * scale
    }

    fun formatNum(time: Int): String {
        return if (time < 10) "0$time" else time.toString()
    }

    fun formatMillisecond(millisecond: Int): String {
        return when {
            millisecond > 99 -> (millisecond / 10).toString()
            millisecond <= 9 -> "0$millisecond"
            else -> millisecond.toString()
        }
    }
}