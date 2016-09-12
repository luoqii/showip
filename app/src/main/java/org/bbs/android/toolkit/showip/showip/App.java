package org.bbs.android.toolkit.showip.showip;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import org.bbs.android.commonlib.AlwaysOnTopWindow;

import java.io.IOException;

/**
 * Created by qiiluo on 9/12/16.
 */
public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private AlwaysOnTopWindow mWindow;
    private TextView mIpText;

    @Override
    public void onCreate() {
        super.onCreate();

        mWindow = new Window(this);
        mWindow = new Window(this);
        mWindow.setContentView(R.layout.showip);
        mWindow.show();

        updateIp();
    }

    void updateIp(){

        mIpText = (TextView) mWindow.getContentView().findViewById(R.id.ip);
        mIpText.setText("ip: " + getIpStr());
        mIpText.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateIp();
            }
        }, 10 * 1000);
    }

    public static class Window extends  AlwaysOnTopWindow {

        private TextView mIpText;
        public Window(Context context) {
            super(context);
        }

        @Override
        protected WindowManager.LayoutParams onCreateLayoutParams() {
            WindowManager.LayoutParams p = super.onCreateLayoutParams();
            p.format = PixelFormat.TRANSLUCENT;
            return p;
        }

//        @Override
//        public void show() {
//            mIpText = (TextView) getContentView().findViewById(R.id.ip);
//            updateIp();
//        }

//        void updateIp(){
//            mIpText.setText("ip:" + getIpStr());
//            mIpText.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    updateIp();
//                }
//            }, 10 * 1000);
//        }
    }

    String getIpStr(){
//        try {
//            Process p = new ProcessBuilder().command("sh getprop dhcp.eth0.ipaddress").start();
//            byte[] buffers = new byte[64];
//            p.getOutputStream().write(buffers);
//            return new String(buffers);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            Process p = new ProcessBuilder().command("sh netcfg | grep \"eth0\"").start();
//            byte[] buffers = new byte[64];
//            p.getOutputStream().write(buffers);
//            return new String(buffers);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ShellUtils.CommandResult r = ShellUtils.execCommand("netcfg | grep \"eth0\"\"", true);
//        Log.d(TAG, "r: " + r);
//        r = ShellUtils.execCommand("getprop dhcp.eth0.ipaddress", true);
//        Log.d(TAG, "r: " + r);
//        return r.toString();
//
//            WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//            Log.d(TAG,"ip: " + ip);

//        return "not exist";

        ShellUtils.CommandResult r = null;
        r = ShellUtils.execCommand("netcfg | grep \"eth0\"\"", true);
        Log.d(TAG, "r: " + r);
        r = ShellUtils.execCommand("getprop dhcp.eth0.ipaddress", false);
        Log.d(TAG, "r: " + r);
        return r.toString();
    }
}
