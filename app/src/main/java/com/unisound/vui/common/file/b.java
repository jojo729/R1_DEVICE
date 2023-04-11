package com.unisound.vui.common.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public final char[] f367a = {'R', 'I', 'F', 'F'};
    public int b;
    public char[] c = {'W', 'A', 'V', 'E'};
    public char[] d = {'f', 'm', 't', ' '};
    public int e;
    public short f;
    public short g;
    public int h;
    public int i;
    public short j;
    public short k;
    public char[] l = {'d', 'a', 't', 'a'};
    public int m;

    private void a(ByteArrayOutputStream byteArrayOutputStream, int i2) throws IOException {
        byte[] bArr = new byte[2];
        bArr[1] = (byte) ((i2 << 16) >> 24);
        bArr[0] = (byte) ((i2 << 24) >> 24);
        byteArrayOutputStream.write(bArr);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, char[] cArr) {
        for (char c2 : cArr) {
            byteArrayOutputStream.write(c2);
        }
    }

    private void b(ByteArrayOutputStream byteArrayOutputStream, int i2) throws IOException {
        byte[] bArr = new byte[4];
        bArr[3] = (byte) (i2 >> 24);
        bArr[2] = (byte) ((i2 << 8) >> 24);
        bArr[1] = (byte) ((i2 << 16) >> 24);
        bArr[0] = (byte) ((i2 << 24) >> 24);
        byteArrayOutputStream.write(bArr);
    }

    public byte[] a() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(byteArrayOutputStream, this.f367a);
        b(byteArrayOutputStream, this.b);
        a(byteArrayOutputStream, this.c);
        a(byteArrayOutputStream, this.d);
        b(byteArrayOutputStream, this.e);
        a(byteArrayOutputStream, this.f);
        a(byteArrayOutputStream, this.g);
        b(byteArrayOutputStream, this.h);
        b(byteArrayOutputStream, this.i);
        a(byteArrayOutputStream, this.j);
        a(byteArrayOutputStream, this.k);
        a(byteArrayOutputStream, this.l);
        b(byteArrayOutputStream, this.m);
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
}
