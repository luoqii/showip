package org.bbs.android.toolkit.showip.showip;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import org.bbs.android.log.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qiiluo on 9/13/16.
 */
public class PersistService extends Service {
    private static final long INTERVAL = 7 * 1000;
    private static final String TAG = PersistService.class.getSimpleName();

    private int mCount;

    public static void start(Context context){
        context.startService(new Intent(context, PersistService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mCount++;
                Log.d(TAG, "run count:" + mCount);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, INTERVAL);

        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, PersistService.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
