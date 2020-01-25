package antoninovitale.punkapi.app.util

import android.util.Log
import antoninovitale.punkapi.app.BuildConfig

/**
 * Created by a.vitale on 29/08/2017.
 */
object DebugLog {
    fun log(tag: String?, message: String?, e: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message ?: "", e)
        }
    }

    fun log(tag: String?, message: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message ?: "")
        }
    }
}