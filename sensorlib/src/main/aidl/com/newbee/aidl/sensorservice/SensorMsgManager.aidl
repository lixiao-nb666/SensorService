// SensorMsgManager.aidl
package com.newbee.aidl.sensorservice;
import com.newbee.aidl.sensorservice.SensorMsg;
import com.newbee.aidl.sensorservice.SensorMsgListen;
// Declare any non-default types here with import statements

interface SensorMsgManager {
     void sendMsg(in SensorMsg sensorMsg);
     void registerReceiveListener(SensorMsgListen listener);
     void unregisterReceiveListener(SensorMsgListen listener);

}