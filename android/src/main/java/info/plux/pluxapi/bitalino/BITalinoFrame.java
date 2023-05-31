/*
*
* Copyright (c) PLUX S.A., All Rights Reserved.
* (www.plux.info)
*
* This software is the proprietary information of PLUX S.A.
* Use is subject to license terms.
*
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
    private int hours, minutes, seconds, milliseconds;

    public BITalinoFrame(String identifier, int seq, int[] analog, int[] digital) {
        this.identifier = identifier;
        this.seq = seq;
        this.analog = analog;
        this.digital = digital;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
    }

    public BITalinoFrame(String identifier, int seq, int[] analog, int[] digital,
                         int hours, int minutes, int seconds, int milliseconds) {
        this.identifier = identifier;
        this.seq = seq;
        this.analog = analog;
        this.digital = digital;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
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

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public String toString(){
        return identifier + ": Seq: " + getSequence() + "; Analog: " + Arrays.toString(analog) + "; Digital: " + Arrays.toString(digital)
                + "; Time: " + getHours() + ":" + getMinutes() + ":" + getSeconds() + "." + getMilliseconds();
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