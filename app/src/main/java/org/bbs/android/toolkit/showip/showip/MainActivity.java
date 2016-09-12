package org.bbs.android.toolkit.showip.showip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getIpStr();
    }


    String getIpStr(){
        try {
            Process p = new ProcessBuilder().command("sh getprop dhcp.eth0.ipaddress").start();
            byte[] buffers = new byte[64];
            p.getOutputStream().write(buffers);
            return new String(buffers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process p = new ProcessBuilder().command("sh netcfg | grep \"eth0\"").start();
            byte[] buffers = new byte[64];
            p.getOutputStream().write(buffers);
            return new String(buffers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShellUtils.CommandResult r = ShellUtils.execCommand("netcfg | grep \"eth0\"\"", false);
        Log.d(TAG, "r: " + r);
        r = ShellUtils.execCommand("getprop dhcp.eth0.ipaddress", false);
        Log.d(TAG, "r: " + r);
        return r.toString();
//
//            WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//            Log.d(TAG,"ip: " + ip);

//        return "not exist";
    }
}
