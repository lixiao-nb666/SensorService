package com.newbee.sensorlib.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;


import com.newbee.aidl.sensorservice.SensorMsgManager;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/1/18 0018 9:50
 */
public class SensorAidlServiceDao {
    private final String tag=getClass().getSimpleName()+">>>>";
    private Context context;
    private ServiceConnection sc;
    private SensorMsgManager sensorMsgManager;
    private Listen listen;


    public SensorAidlServiceDao(Context context,Listen listen) {
        this.context = context.getApplicationContext();
        this.listen=listen;
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                sensorMsgManager=SensorMsgManager.Stub.asInterface(service);
                listen.isConnect();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
    }

    public SensorMsgManager getSensorMsgManager(){
        return sensorMsgManager;
    }


    public void startServiceIsBind() {
        try {
            Log.i(tag,"startServiceIsBind()");
            Intent intent = new Intent();
            //跨进程通信需要使用action启动
            intent.setAction("com.aidl.service.sensorservice");
            //android5.0之后，如果servicer不在同一个App的包中，需要设置service所在程序的包名
            intent.setPackage("com.newbee.sensorservice");

            context.getApplicationContext().bindService(intent, sc, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.i(tag,"startServiceIsBind()err:"+e.toString());
        }
    }




    public void stopServiceIsBind() {
        try {
            Log.i(tag,"stopServiceIsBind()");
            context.unbindService(sc);
            listen.disConnect();
        } catch (Exception e) {
            Log.i(tag,"stopServiceIsBind()err:"+e.toString());
        }
    }


    public interface Listen{

        public void isConnect();

        public void disConnect();
    }

}
