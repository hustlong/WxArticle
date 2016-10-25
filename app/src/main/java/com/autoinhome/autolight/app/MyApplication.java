package com.autoinhome.autolight.app;

import android.app.Application;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import io.yunba.android.manager.YunBaManager;

/**
 * Created by yanglong on 2016/10/21.
 */

public class MyApplication extends Application {

    private static String TAG = "MyApplication";
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        YunBaManager.start(getApplicationContext());
        YunBaManager.subscribe(getApplicationContext(), new String[]{"autolight"}, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken arg0) {
                Log.d(TAG, "Subscribe topic succeed");
            }
            @Override
            public void onFailure(IMqttToken arg0, Throwable arg1) {
                Log.d(TAG, "Subscribe topic failed");
            }
        });
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
