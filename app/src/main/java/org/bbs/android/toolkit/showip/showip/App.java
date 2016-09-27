package org.bbs.android.toolkit.showip.showip;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.widget.TextView;

import org.bbs.android.commonlib.AlwaysOnTopWindow;
import org.bbs.android.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by qiiluo on 9/12/16.
 */
public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private Window   mWindow;
    private TextView mIpText;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Logger l = Logger.getAnonymousLogger();
//            File f = new File(Environment.getExternalStorageDirectory(),
//                    getApplication().getPackageName() + "/log");
//            boolean mk = f.mkdirs();
//            Log.d(TAG, "mk:" + mk);
//            Log.d(TAG, "isExternalStorageWritable:" + isExternalStorageWritable());
//            Log.d(TAG, "isExternalStorageReadable:" + isExternalStorageReadable());

            File logDir = new File(getExternalFilesDir(null), "log%g");
            Log.d(TAG, "logDir:" + logDir.getParent());
            FileHandler h = new FileHandler(logDir.getPath() + ".txt",
                    1 * 1024 * 1024,
                    5);            h.setFormatter(new Log.SimpleFormatter());
            l.addHandler(h);
            l.setLevel(Level.ALL);
            Log.setLogger(l);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PersistService.start(this);
        mWindow = new Window(this);
        mWindow.setContentView(R.layout.showip);
        mWindow.show();
    }

    public static class Window extends  AlwaysOnTopWindow {

        private static Window sInstance;
        private TextView mIpText;

        public static Window getInstance(Context context){
            if (null == sInstance){
                sInstance = new Window(context);
            }

            return sInstance;
        }

        private Window(Context context) {
            super(context);
        }

        @Override
        protected WindowManager.LayoutParams onCreateLayoutParams() {
            WindowManager.LayoutParams p = super.onCreateLayoutParams();
            p.format = PixelFormat.TRANSLUCENT;
            return p;
        }

        @Override
        public void show() {
            super.show();

            updateIp();
        }

        public void updateIp(){

            mIpText = (TextView) getContentView().findViewById(R.id.ip);
            mIpText.setText("ip: " + getIpStr());
            mIpText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateIp();
                }
            }, 10 * 1000);
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

            // XXX first true, then false, yes believe me, order is import.
            ShellUtils.CommandResult r = null;
            r = ShellUtils.execCommand("netcfg | grep \"eth0\"\"", true);
            Log.d(TAG, "r: " + r);
            r = ShellUtils.execCommand("getprop dhcp.eth0.ipaddress", false);
            Log.d(TAG, "r: " + r);
            return r.successMsg;
        }
    }

}
