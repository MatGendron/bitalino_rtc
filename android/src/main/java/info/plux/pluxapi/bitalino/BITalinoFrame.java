/*
*
* Copyright (c) PLUX S.A., All Rights Reserved.
* (www.plux.info)
*
* This software is the proprietary information of PLUX S.A.
* Use is subject to license terms.
*
* @modifiedby    Mathis Gendron
* @date          June 2023 
*/
package info.plux.pluxapi.bitalino;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

//RTC: Need to add fields for time data
public class BITalinoFrame implements Parcelable {
    private String identifier;
    private int seq;
    private int[] analog = new int[6];
    private int[] digital = new int[4];
    private int BIThours, BITminutes, BITseconds, BITmilliseconds;
    private int APIhours, APIminutes, APIseconds, APImilliseconds;

    public BITalinoFrame(String identifier, int seq, int[] analog, int[] digital) {
        this.identifier = identifier;
        this.seq = seq;
        this.analog = analog;
        this.digital = digital;
        this.BIThours = 0;
        this.BITminutes = 0;
        this.BITseconds = 0;
        this.BITmilliseconds = 0;
        this.APIhours = 0;
        this.APIminutes = 0;
        this.APIseconds = 0;
        this.APImilliseconds = 0;
    }

    public BITalinoFrame(String identifier, int seq, int[] analog, int[] digital,
                         int BIThours, int BITminutes, int BITseconds, int BITmilliseconds,
                         int APIhours, int APIminutes, int APIseconds, int APImilliseconds) {
        this.identifier = identifier;
        this.seq = seq;
        this.analog = analog;
        this.digital = digital;
        this.BIThours = BIThours;
        this.BITminutes = BITminutes;
        this.BITseconds = BITseconds;
        this.BITmilliseconds = BITmilliseconds;
        this.APIhours = APIhours;
        this.APIminutes = APIminutes;
        this.APIseconds = APIseconds;
        this.APImilliseconds = APImilliseconds;
    }

    public BITalinoFrame(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getSequence() {
        return seq;
    }

    public void setSequence(int seq) {
        this.seq = seq;
    }

    public int getAnalog(final int pos) {
        return analog[pos];
    }

    public void  setAnalog(final int pos, final int value) throws IndexOutOfBoundsException {
        this.analog[pos] = value;
    }

    public int[] getAnalogArray() {
        return analog;
    }

    public void  setAnalogArray(int[] analog){
        this.analog = analog;
    }

    public int getDigital(final int pos) {
        return digital[pos];
    }

    public void setDigital(final int pos, final int value) throws IndexOutOfBoundsException {
        this.digital[pos] = value;
    }

    public int[] getDigitalArray() {
        return digital;
    }

    public void  setDigitalArray(int[] digital){
        this.digital = digital;
    }

    public void setBIThours(int BIThours) {
        this.BIThours = BIThours;
    }

    public int getBIThours() {
        return BIThours;
    }

    public void setBITminutes(int BITminutes) {
        this.BITminutes = BITminutes;
    }

    public int getBITminutes() {
        return BITminutes;
    }

    public void setBITseconds(int BITseconds) {
        this.BITseconds = BITseconds;
    }

    public int getBITseconds() {
        return BITseconds;
    }

    public void setBITmilliseconds(int BITmilliseconds) {
        this.BITmilliseconds = BITmilliseconds;
    }

    public int getBITmilliseconds() {
        return BITmilliseconds;
    }

    public void setAPIhours(int APIhours) {
        this.APIhours = APIhours;
    }

    public int getAPIhours() {
        return APIhours;
    }

    public void setAPIminutes(int APIminutes) {
        this.APIminutes = APIminutes;
    }

    public int getAPIminutes() {
        return APIminutes;
    }

    public void setAPIseconds(int APIseconds) {
        this.APIseconds = APIseconds;
    }

    public int getAPIseconds() {
        return APIseconds;
    }

    public void setAPImilliseconds(int APImilliseconds) {
        this.APImilliseconds = APImilliseconds;
    }

    public int getAPImilliseconds() {
        return APImilliseconds;
    }

    public String toString(){
        return identifier + ": Seq: " + getSequence() + "; Analog: " + Arrays.toString(analog) + "; Digital: " + Arrays.toString(digital)
                + "; BITtime: " + getBIThours() + ":" + getBITminutes() + ":" + getBITseconds() + "." + getBITmilliseconds()
                + "; APItime: " + getAPIhours() + ":" + getAPIminutes() + ":" + getAPIseconds() + "." + getAPImilliseconds();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeInt(this.seq);
        dest.writeIntArray(this.analog);
        dest.writeIntArray(this.digital);
    }

    protected BITalinoFrame(Parcel in) {
        this.identifier = in.readString();
        this.seq = in.readInt();
        this.analog = in.createIntArray();
        this.digital = in.createIntArray();
    }

    public static final Creator<BITalinoFrame> CREATOR = new Creator<BITalinoFrame>() {
        @Override
        public BITalinoFrame createFromParcel(Parcel source) {
            return new BITalinoFrame(source);
        }

        @Override
        public BITalinoFrame[] newArray(int size) {
            return new BITalinoFrame[size];
        }
    };
}