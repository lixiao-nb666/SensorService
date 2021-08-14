package com.newbee.sensorservice.activity;

import android.os.IBinder;
import android.os.RemoteException;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.newbee.aidl.sensorservice.SensorMsg;
import com.newbee.aidl.sensorservice.SensorMsgListen;
import com.newbee.aidl.sensorservice.SensorMsgManager;
import com.newbee.sensorservice.R;
import com.newbee.sensorlib.service.SensorAidlServiceDao;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/8/14 0014 10:16
 */
public class TestActivity extends BaseCompatActivity {
    private SensorAidlServiceDao sensorAidlServiceDao;
    private SensorMsgManager sensorMsgManager;
    private SensorMsgListen sensorMsgListen=new SensorMsgListen.Stub(){
        @Override
        public void getMsg(SensorMsg sensorMsg) throws RemoteException {
            LG.i(tag,"sendMsg------:"+sensorMsg);
        }
    };

    private SensorAidlServiceDao.Listen listen=new SensorAidlServiceDao.Listen() {
        @Override
        public void isConnect() {
            LG.i(tag,"aidl链接成功");
            sensorMsgManager=sensorAidlServiceDao.getSensorMsgManager();
            if(null!=sensorMsgManager){
                try {
                    sensorMsgManager.registerReceiveListener(sensorMsgListen);

                    sensorMsgManager.sendMsg(new SensorMsg(1,"12345"));
                }catch (Exception e){
                    LG.i(tag,"sendMsg------err:"+e);
                }
            }


        }

        @Override
        public void disConnect() {
            LG.i(tag,"aidl链接失败");
        }
    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        sensorAidlServiceDao=new SensorAidlServiceDao(this,listen) ;
    }

    @Override
    public void initControl() {
        sensorAidlServiceDao.startServiceIsBind();
    }

    @Override
    public void closeActivity() {
        sensorAidlServiceDao.stopServiceIsBind();
    }

    @Override
    public void viewIsShow() {

    }

    @Override
    public void viewIsPause() {

    }

    @Override
    public void changeConfig() {

    }
}
