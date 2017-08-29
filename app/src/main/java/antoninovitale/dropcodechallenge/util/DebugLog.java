package antoninovitale.dropcodechallenge.util;

import android.util.Log;

import antoninovitale.dropcodechallenge.BuildConfig;

/**
 * Created by a.vitale on 29/08/2017.
 */
public class DebugLog {

    public static void log(String tag, String message, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message, e);
        }
    }

    public static void log(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

}