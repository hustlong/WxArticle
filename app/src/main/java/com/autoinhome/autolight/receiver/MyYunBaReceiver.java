package com.autoinhome.autolight.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.autoinhome.autolight.util.YunBaUtil;

import io.yunba.android.manager.YunBaManager;

/**
 * Created by yanglong on 2016/10/24.
 */

public class MyYunBaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (YunBaManager.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {

            String topic = intent.getStringExtra(YunBaManager.MQTT_TOPIC);
            String msg = intent.getStringExtra(YunBaManager.MQTT_MSG);

            //在这里处理从服务器发布下来的消息， 比如显示通知栏， 打开 Activity 等等
            YunBaUtil.showNotifation(context, topic, msg);
        }
    }
}
