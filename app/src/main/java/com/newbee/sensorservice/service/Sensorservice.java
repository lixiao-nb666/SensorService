package com.newbee.sensorservice.service;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.service.BaseService;
import com.lixiao.build.util.sensor.SensorListen;
import com.newbee.aidl.sensorservice.SensorMsg;
import com.newbee.aidl.sensorservice.SensorMsgListen;
import com.newbee.aidl.sensorservice.SensorMsgManager;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/8/13 0013 15:01
 */
public class Sensorservice extends BaseService {
    private RemoteCallbackList<SensorMsgListen> mReceiveListener = new RemoteCallbackList<SensorMsgListen>();


    @Override
    public void init() {

    }

    @Override
    public void start(Intent intent, int flags, int startId) {

    }

    @Override
    public void close() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends SensorMsgManager.Stub{

        @Override
        public void sendMsg(SensorMsg sensorMsg) throws RemoteException {
            LG.i(tag,"sendMsg:"+sensorMsg);
            mReceiveListener.getBroadcastItem(0).getMsg(new SensorMsg(1,"5555666"));
        }

        @Override
        public void registerReceiveListener(SensorMsgListen listener) throws RemoteException {
            mReceiveListener.register(listener);
        }

        @Override
        public void unregisterReceiveListener(SensorMsgListen listener) throws RemoteException {
            boolean success = mReceiveListener.unregister(listener);
            if (success){
                Log.d("tag","===  解除注册成功");
            }else {
                Log.d("tag","===  解除注册失败 ");
            }
        }
    }
}
