package org.bbs.android.toolkit.showip.showip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qiiluo on 9/13/16.
 */
public class BR extends BroadcastReceiver {

    private static final String TAG = BR.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run count");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 60 * 1000);
    }
}
