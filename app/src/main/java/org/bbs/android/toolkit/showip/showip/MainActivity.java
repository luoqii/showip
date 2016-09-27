package org.bbs.android.toolkit.showip.showip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.bbs.android.log.Log;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private App.Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWindow = App.Window.getInstance(null);
    }

    public void updateWindow(){
        WindowManager.LayoutParams param = mWindow.getLayoutParam();

        mWindow.updateLayoutParam(param);
    }

}
