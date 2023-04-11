package com.unisound.vui.transport;

import android.os.Parcel;
import android.os.Parcelable;

public class DataStream implements Parcelable {
    public static final Parcelable.Creator<DataStream> CREATOR = new Parcelable.Creator<DataStream>() {
        /* class com.unisound.vui.transport.DataStream.AnonymousClass1 */

        /* renamed from: a */
        public DataStream createFromParcel(Parcel parcel) {
            return new DataStream(parcel);
        }

        /* renamed from: a */
        public DataStream[] newArray(int i) {
            return new DataStream[i];
        }
    };
    private byte[] dataBytes;

    private DataStream(Parcel parcel) {
        readFromParcel(parcel);
    }

    public DataStream(byte[] bArr) {
        this.dataBytes = bArr;
    }

    public int describeContents() {
        return 0;
    }

    public byte[] getDataBytes() {
        return this.dataBytes;
    }

    public void readFromParcel(Parcel parcel) {
        byte[] bArr = new byte[parcel.readInt()];
        this.dataBytes = bArr;
        parcel.readByteArray(bArr);
    }

    public void setDataBytes(byte[] bArr) {
        this.dataBytes = bArr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.dataBytes.length);
        parcel.writeByteArray(this.dataBytes);
    }
}
