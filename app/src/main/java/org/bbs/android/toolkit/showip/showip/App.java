package org.bbs.android.toolkit.showip.showip;

import android.app.Application;

import org.bbs.android.commonlib.AlwaysOnTopWindow;

/**
 * Created by qiiluo on 9/12/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AlwaysOnTopWindow w = new AlwaysOnTopWindow(this);
        w.setContentView(R.layout.showip);
        w.show();
    }
}
