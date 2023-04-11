package com.unisound.client;

import android.os.Environment;
import com.unisound.common.y;
import com.unisound.jni.AEC;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class IAudioSourceAEC implements IAudioSource {
    private static final String i = "AudioSourceImpl";
    private static boolean j = false;
    private static final int k = 5;
    private static int l = SpeechConstants.ASR_BEST_RESULT_RETURN;

    /* renamed from: a  reason: collision with root package name */
    byte[] f142a = new byte[l];
    byte[] b = {100};
    int c = 0;
    byte[] d = null;
    byte[] e = null;
    String f = (Environment.getExternalStorageDirectory().getPath() + "/YunZhiSheng/aec/");
    String g = "";
    AEC h = null;
    private boolean m = false;
    private boolean n = false;
    private BlockingQueue<byte[]> o = new LinkedBlockingQueue();
    private BlockingQueue<byte[]> p = new LinkedBlockingQueue();
    private float q = 0.0f;
    private float r = 0.0f;
    private int s = 0;
    private boolean t = false;
    private boolean u = false;

    /* access modifiers changed from: package-private */
    public class AECThread extends Thread {
        private AECThread() {
        }

        public void run() {
            super.run();
            while (IAudioSourceAEC.this.isRecordingStart() && !IAudioSourceAEC.this.m) {
                try {
                    byte[] bArr = (byte[]) IAudioSourceAEC.this.p.poll(5, TimeUnit.MILLISECONDS);
                    if (bArr != null) {
                        if (bArr.length == 1 && bArr[0] == 100) {
                            IAudioSourceAEC.this.m = true;
                        } else {
                            IAudioSourceAEC.saveRecordingData(bArr, IAudioSourceAEC.this.f + IAudioSourceAEC.this.g + "_out_mic.pcm");
                            IAudioSourceAEC.this.e = IAudioSourceAEC.this.h.process(bArr, null);
                        }
                        a(e);
                    } else {
                        Thread.sleep(5);
                    }
                } catch (InterruptedException e) {
                    y.c(IAudioSourceAEC.i, "IAudioSourceAEC runAEC interrupt");
                }
            }
            IAudioSourceAEC iAudioSourceAEC = IAudioSourceAEC.this;
            iAudioSourceAEC.e = iAudioSourceAEC.h.getlast();
            IAudioSourceAEC iAudioSourceAEC2 = IAudioSourceAEC.this;
            a(iAudioSourceAEC2.e);
            IAudioSourceAEC.this.n = true;
            IAudioSourceAEC iAudioSourceAEC3 = IAudioSourceAEC.this;
            a(iAudioSourceAEC3.b);
        }
    }

    /* access modifiers changed from: package-private */
    public class recordingThread extends Thread {
        private recordingThread() {
        }

        public void run() {
            super.run();
            while (IAudioSourceAEC.this.isRecordingStart()) {
                IAudioSourceAEC iAudioSourceAEC = IAudioSourceAEC.this;
                int readDataPro = iAudioSourceAEC.readDataPro(iAudioSourceAEC.f142a, IAudioSourceAEC.this.f142a.length);
                if (readDataPro > 0) {
                    if (IAudioSourceAEC.this.p != null) {
                        IAudioSourceAEC.this.p.add(Arrays.copyOfRange(IAudioSourceAEC.this.f142a, 0, readDataPro));
                    }
                } else if (readDataPro < 0) {
                    IAudioSourceAEC.this.p.add(Arrays.copyOfRange(IAudioSourceAEC.this.b, 0, IAudioSourceAEC.this.b.length));
                }
            }
            IAudioSourceAEC.this.setFirstStartRecording(true);
        }
    }

    public IAudioSourceAEC() {
        y.c(i, "IAudioSourceAEC");
        AEC aec = new AEC(16000, 1);
        this.h = aec;
        aec.setOptionInt(0, 600);
        this.h.setOptionInt(2, 1);
        this.h.setOptionInt(3, this.s);
    }

    private int a(int i2, int i3) {
        int i4 = i2 % i3;
        int i5 = i2 / i3;
        return i4 == 0 ? i5 : i5 + 1;
    }

    private int a(byte[] bArr, int i2) {
        int i3;
        try {
            byte[] poll = this.o.poll(5, TimeUnit.MILLISECONDS);
            if (poll == null) {
                return this.n ? -9 : 0;
            }
            i3 = poll.length;
            if (i3 > l) {
                i3 = l;
            }
            System.arraycopy(poll, 0, bArr, 0, i3);
            return i3;
        } catch (InterruptedException e3) {
            i3 = 0;
            y.c(i, "IAudioSourceAEC readBuffer interrupt");
            return i3;
        }
    }

    private void a() {
        this.n = false;
        this.h.reset(c(), d());
        AECThread aECThread = new AECThread();
        aECThread.setPriority(10);
        aECThread.start();
    }

    private static void a(String str) {
        int lastIndexOf;
        if (str != null && (lastIndexOf = str.lastIndexOf(47)) >= 0) {
            new File(str.substring(0, lastIndexOf)).mkdirs();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(byte[] bArr) {
        if (bArr != null) {
            saveRecordingData(bArr, this.f + this.g + "_out_aec.pcm");
            this.o.add(Arrays.copyOfRange(bArr, 0, bArr.length));
        }
    }

    private void b() {
        recordingThread recordingthread = new recordingThread();
        recordingthread.setPriority(10);
        recordingthread.start();
    }

    private float c() {
        return this.q;
    }

    private float d() {
        return this.r;
    }

    public static boolean saveRecordingData(byte[] a0, String s0) {
        java.io.RandomAccessFile a1 = null;
        Throwable a2 = null;
        boolean b0 = j;
        label2: {
            boolean b1 = false;
            label8: {
                label3: {
                    label1: {
                        java.io.IOException a3 = null;
                        if (!b0) {
                            break label1;
                        }
                        com.unisound.client.IAudioSourceAEC.a(s0);
                        label7: try {
                            label4: {
                                Exception a4 = null;
                                label6: {
                                    label5: {
                                        try {
                                            try {
                                                a1 = new java.io.RandomAccessFile(s0, "rw");
                                                break label5;
                                            } catch(Exception a5) {
                                                a4 = a5;
                                            }
                                        } catch(Throwable a6) {
                                            a2 = a6;
                                            break label4;
                                        }
                                        a1 = null;
                                        break label6;
                                    }
                                    try {
                                        a1.seek(a1.length());
                                        a1.write(a0);
                                        break label3;
                                    } catch(Exception a7) {
                                        a4 = a7;
                                    }
                                }
                                a4.printStackTrace();
                                break label7;
                            }
                            a1 = null;
                            break label2;
                        } catch(Throwable a8) {
                            a2 = a8;
                            break label2;
                        }
                        if (a1 == null) {
                            break label1;
                        }
                        try {
                            a1.close();
                            break label1;
                        } catch(java.io.IOException a9) {
                            a3 = a9;
                        }
                        a3.printStackTrace();
                    }
                    b1 = false;
                    break label8;
                }
                try {
                    a1.close();
                    b1 = true;
                } catch(java.io.IOException a10) {
                    a10.printStackTrace();
                    b1 = true;
                }
            }
            return b1;
        }
        label0: {
            java.io.IOException a11 = null;
            if (a1 == null) {
                break label0;
            }
            try {
                a1.close();
                break label0;
            } catch(java.io.IOException a12) {
                a11 = a12;
            }
            a11.printStackTrace();
        }
        return false;
    }


    public boolean isFirstStartRecording() {
        return this.u;
    }

    public boolean isRecordingStart() {
        return this.t;
    }

    @Override // com.unisound.client.IAudioSource
    public int readData(byte[] bArr, int i2) {
        if (isFirstStartRecording()) {
            setFirstStartRecording(false);
            this.g = String.valueOf(System.currentTimeMillis());
            BlockingQueue<byte[]> blockingQueue = this.p;
            if (blockingQueue != null) {
                blockingQueue.clear();
            }
            BlockingQueue<byte[]> blockingQueue2 = this.o;
            if (blockingQueue2 != null) {
                blockingQueue2.clear();
            }
            b();
            this.m = false;
            a();
        }
        return a(bArr, i2);
    }

    public abstract int readDataPro(byte[] bArr, int i2);

    public void release() {
        this.h.release();
        this.h = null;
    }

    public void setDebug(boolean z) {
        j = z;
    }

    public void setEngineDebug(boolean z) {
        AEC aec;
        int i2;
        if (z) {
            aec = this.h;
            if (aec != null) {
                i2 = 1;
            } else {
                return;
            }
        } else {
            aec = this.h;
            if (aec != null) {
                i2 = 0;
            } else {
                return;
            }
        }
        aec.setOptionInt(4, i2);
    }

    public void setFirstStartRecording(boolean z) {
        this.u = z;
    }

    public int setFrameAjustUnit(int i2) {
        AEC aec = this.h;
        if (aec != null) {
            return aec.setOptionInt(5, i2);
        }
        return 0;
    }

    public void setMicChannel(int i2) {
        AEC aec = this.h;
        if (aec != null) {
            aec.setOptionInt(3, i2);
        }
    }

    public void setRecordingStart(boolean z) {
        this.t = z;
    }

    @Override // com.unisound.client.IAudioSource
    public int writeData(byte[] bArr, int i2) {
        int length = bArr.length;
        if (length > 0) {
            return writeDataPro(Arrays.copyOfRange(bArr, 0, length), i2);
        }
        return 0;
    }

    public abstract int writeDataPro(byte[] bArr, int i2);
}
