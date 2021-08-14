// SensorMsgReceive.aidl
package com.newbee.aidl.sensorservice;
import com.newbee.aidl.sensorservice.SensorMsg;
// Declare any non-default types here with import statements

interface SensorMsgListen {
  void getMsg(in SensorMsg sensorMsg);
}