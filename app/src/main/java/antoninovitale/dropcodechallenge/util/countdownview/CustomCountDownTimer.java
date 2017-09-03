package antoninovitale.dropcodechallenge.util.countdownview;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Created by iWgang on 15/10/18.
 * https://github.com/iwgang/CountdownView
 */
abstract class CustomCountDownTimer {
    private static final int MSG = 1;

    private final long mMillisInFuture;

    private final long mCountdownInterval;

    private long mStopTimeInFuture;

    private long mPauseTimeInFuture;

    private boolean isStop = false;

    private boolean isPause = false;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CustomCountDownTimer.this) {
                if (isStop || isPause) {
                    return;
                }

                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (millisLeft <= 0) {
                    onFinish();
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);

                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval;

                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };

    CustomCountDownTimer(long millisInFuture, long countDownInterval) {
        if (countDownInterval > 1000) millisInFuture += 15;
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    private synchronized CustomCountDownTimer start(long millisInFuture) {
        isStop = false;
        isPause = false;
        if (millisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + millisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    synchronized final void start() {
        start(mMillisInFuture);
    }

    synchronized final void stop() {
        isStop = true;
        mHandler.removeMessages(MSG);
    }

    synchronized final void pause() {
        if (isStop) return;

        isPause = true;
        mPauseTimeInFuture = mStopTimeInFuture - SystemClock.elapsedRealtime();
        mHandler.removeMessages(MSG);
    }

    synchronized final void restart() {
        if (isStop || !isPause) return;

        isPause = false;
        start(mPauseTimeInFuture);
    }

    synchronized boolean isPaused() {
        return isPause;
    }

    synchronized boolean isStopped() {
        return isStop;
    }

    synchronized boolean isRunning() {
        return !isStop && !isPause;
    }

    public abstract void onTick(long millisUntilFinished);

    public abstract void onFinish();

}