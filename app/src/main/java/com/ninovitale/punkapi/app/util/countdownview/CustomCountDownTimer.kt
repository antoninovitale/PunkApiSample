package com.ninovitale.punkapi.app.util.countdownview

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.os.SystemClock

/**
 * Created by iWgang on 15/10/18.
 * https://github.com/iwgang/CountdownView
 */
internal abstract class CustomCountDownTimer(millisInFuture: Long, countDownInterval: Long) {
    private val mMillisInFuture: Long
    private var mCountdownInterval: Long = 0
    private var mStopTimeInFuture: Long = 0
    private var mPauseTimeInFuture: Long = 0
    @get:Synchronized
    var isStopped = false
        private set
    @get:Synchronized
    var isPaused = false
        private set

    init {
        var millisInFuture = millisInFuture
        if (countDownInterval > 1000) millisInFuture += 15
        mMillisInFuture = millisInFuture
        mCountdownInterval = countDownInterval
    }

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            synchronized(this@CustomCountDownTimer) {
                if (isStopped || isPaused) {
                    return
                }
                val millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime()
                if (millisLeft <= 0) {
                    onFinish()
                } else {
                    val lastTickStart = SystemClock.elapsedRealtime()
                    onTick(millisLeft)
                    // take into account user's onTick taking time to execute
                    var delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime()
                    // special case: user's onTick took more than interval to complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval
                    sendMessageDelayed(obtainMessage(MSG), delay)
                }
            }
        }
    }

    @Synchronized
    private fun start(millisInFuture: Long): CustomCountDownTimer {
        isStopped = false
        isPaused = false
        if (millisInFuture <= 0) {
            onFinish()
            return this
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + millisInFuture
        mHandler.sendMessage(mHandler.obtainMessage(MSG))
        return this
    }

    @Synchronized
    fun start() {
        start(mMillisInFuture)
    }

    @Synchronized
    fun stop() {
        isStopped = true
        mHandler.removeMessages(MSG)
    }

    @Synchronized
    fun pause() {
        if (isStopped) return
        isPaused = true
        mPauseTimeInFuture = mStopTimeInFuture - SystemClock.elapsedRealtime()
        mHandler.removeMessages(MSG)
    }

    @Synchronized
    fun restart() {
        if (isStopped || !isPaused) return
        isPaused = false
        start(mPauseTimeInFuture)
    }

    @get:Synchronized
    val isRunning: Boolean
        get() = !isStopped && !isPaused

    abstract fun onTick(millisUntilFinished: Long)
    abstract fun onFinish()

    companion object {
        private const val MSG = 1
    }
}