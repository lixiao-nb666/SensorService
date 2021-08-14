package com.newbee.aidl.sensorservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/8/13 0013 15:10
 */
public class SensorMsg implements Parcelable {
    private int code;
    private String gs;
    private long time;


    public SensorMsg(int code, String gs){
        this.code=code;
        this.gs=gs;
    }


    protected SensorMsg(Parcel in) {
        code = in.readInt();
        gs = in.readString();
        time = in.readLong();
    }

    public static final Creator<SensorMsg> CREATOR = new Creator<SensorMsg>() {
        @Override
        public SensorMsg createFromParcel(Parcel in) {
            return new SensorMsg(in);
        }

        @Override
        public SensorMsg[] newArray(int size) {
            return new SensorMsg[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeLong(time);
        dest.writeString(gs);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SensorMsg{" +
                "code=" + code +
                ", gs='" + gs + '\'' +
                ", time=" + time +
                '}';
    }
}
