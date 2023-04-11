package com.unisound.vui.common.file;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Base64;
import com.unisound.sdk.c;
import com.unisound.vui.util.LogMgr;
import org.apache.commons.io.FileUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public final class FileHelper {
    public static final String CUSTOM_FILE_DIR = "system/unisound";
    public static final String GRAMMAR_RELATIVE_PATH = "unisound/asrfix/jsgf_model";
    public static final String JSGF_RELATIVE_PATH = "unisound/jsgf_clg";
    public static final String NLU_RELATIVE_PATH = "unisound/nlu";
    public static final String SAVE_ASR_RECORDING_PATH = "unisound/record/asr/";
    public static final String SAVE_TTS_RECORDING_PATH = "unisound/record/tts/";
    public static final String SAVE_WAKEUP_RECORDING_PATH = "unisound/record/wakeup/";
    private static final String TAG = "FileHelper";
    public static final String TTS_MODEL_PATH = "system/unisound/ttsmodel";
    public static final String TTS_PCM_PATH = "system/unisound/audio";
    public static final String TTS_PCM_WAKEUP_PATH = "system/unisound/audio/wakeup";

    private FileHelper() {
        throw new AssertionError("");
    }

    public static Bitmap base64ToBitmap(String string) {
        try {
            byte[] decode = Base64.decode(string, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean copyDirectoryFromAssets(Context context, String srcDir, File destDir) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        try {
            AssetManager assets = context.getAssets();
            String[] list = assets.list(srcDir);
            for (String str : list) {
                String str2 = srcDir + File.separator + str;
                InputStream open = assets.open(str2);
                File file = new File(destDir, str);
                if (!"ttsmodel".equals(srcDir)) {
                    FileUtils.copyInputStreamToFile(open, file);
                } else if (!file.exists()) {
                    LogMgr.d(TAG, "outPutFile is not exist");
                    FileUtils.copyInputStreamToFile(open, file);
                } else {
                    boolean isSameFileHeader = isSameFileHeader(open, file, str);
                    LogMgr.d(TAG, "2 file header is same " + isSameFileHeader);
                    if (!isSameFileHeader) {
                        FileUtils.copyInputStreamToFile(assets.open(str2), file);
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyDirectoryFromAssets(Context context, String srcDir, String destDir) {
        return copyDirectoryFromAssets(context, srcDir, new File(destDir));
    }

    public static boolean copyFileFromAssets(Context context, String srcDir, String srcFile, File destDir) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        try {
            FileUtils.copyInputStreamToFile(context.getAssets().open(srcDir + File.separator + srcFile), new File(destDir, srcFile));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAllFile(String path) {
        boolean z = false;
        File file = new File(path);
        if (!(file.exists() && file.isDirectory())) {
            return false;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            return true;
        }
        for (int i = 0; i < list.length; i++) {
            File file2 = path.endsWith(File.separator) ? new File(path + list[i]) : new File(path + File.separator + list[i]);
            if (file2.isFile()) {
                file2.delete();
            }
            if (file2.isDirectory()) {
                deleteAllFile(path + MqttTopic.TOPIC_LEVEL_SEPARATOR + list[i]);
                z = true;
            } else {
                z = z;
            }
        }
        return z;
    }

    public static String formatPcmToWav(String path, String fileName) {
        String fileName2 = fileName.replace(".pcm", "");
        String str = path + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileName2 + ".wav";
        try {
            FileInputStream fileInputStream = new FileInputStream(path + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileName2 + ".pcm");
            byte[] bArr = new byte[1024];
            ArrayList arrayList = new ArrayList();
            while (true) {
                byte[] bArr2 = new byte[1024];
                if (fileInputStream.read(bArr, 0, bArr.length) <= 0) {
                    a.a(path, fileName2 + ".wav", arrayList);
                    try {
                        fileInputStream.close();
                        return str;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    arrayList.add(bArr2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String generateFileName(String ex) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + "." + ex;
    }

    public static String getASRSavedRecordingPath(int type) {
        String path = Environment.getExternalStorageDirectory().getPath();
        switch (type) {
            case 0:
                String str = path + File.separator + SAVE_WAKEUP_RECORDING_PATH;
                pathExist(str);
                return str + generateFileName(c.b);
            case 1:
                String str2 = path + File.separator + SAVE_ASR_RECORDING_PATH;
                pathExist(str2);
                return str2 + generateFileName(c.b);
            case 2:
                String str3 = path + File.separator + SAVE_TTS_RECORDING_PATH;
                pathExist(str3);
                return str3;
            default:
                return null;
        }
    }

    public static String getExternalStoragePath() {
        return isMediaMounted() ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    public static String[] getFileListFromAssets(Context context, String srcDir) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        try {
            return context.getAssets().list(srcDir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getGrammarDirectory(Context context) {
        if (context != null) {
            return new File(context.getFilesDir(), GRAMMAR_RELATIVE_PATH);
        }
        throw new NullPointerException("context");
    }

    public static File getGrammarFile(Context context, String fileName) {
        return new File(getGrammarDirectory(context), fileName);
    }

    public static File getJSGFDirectory(Context context) {
        if (context != null) {
            return new File(context.getFilesDir(), JSGF_RELATIVE_PATH);
        }
        throw new NullPointerException("context");
    }

    public static File getJSGFFile(Context context, String fileName) {
        return new File(getJSGFDirectory(context), fileName);
    }

    public static File getNluDirectory(Context context) {
        if (context != null) {
            return new File(context.getFilesDir(), NLU_RELATIVE_PATH);
        }
        throw new NullPointerException("context");
    }

    public static File getNluFile(Context context, String fileName) {
        return new File(getNluDirectory(context), fileName);
    }

    public static int getPcmLength(String filePcm) {
        File file = new File(filePcm);
        if (!file.exists()) {
            return 0;
        }
        return ((int) (file.length() / 32)) / 1000;
    }

    public static File getTTSModelDirectory() {
        return new File(TTS_MODEL_PATH);
    }

    public static File getTTSModelFile(String fileName) {
        return new File(getTTSModelDirectory(), fileName);
    }

    public static File getTTSPCMDirectory() {
        return new File(TTS_PCM_PATH);
    }

    public static File getTTSPCMFile(String fileName) {
        return new File(getTTSPCMDirectory(), fileName);
    }

    public static File getWakeupAudioFile(String fileName) {
        return new File(getWakeupPcmDirectory(), fileName);
    }

    public static File getWakeupPcmDirectory() {
        return new File(TTS_PCM_WAKEUP_PATH);
    }

    public static int getWavTime(String file) {
        int i;
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(file);
            i = (int) (Long.valueOf(mediaMetadataRetriever.extractMetadata(9)).longValue() / 1000);
            if (i == 0) {
                return 1;
            }
        } catch (Exception e) {
            i = 1;
        }
        return i;
    }

    public static boolean isFileExist(String path) {
        return new File(path).exists();
    }

    public static boolean isFileExists(String absolutePath) {
        return new File(absolutePath).exists();
    }

    public static boolean isMediaMounted() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static boolean isSameFileHeader(InputStream in, File destFile, String srcFile) {
        LogMgr.d(TAG, "copy file name" + srcFile);
        byte[] bArr = new byte[256];
        byte[] bArr2 = new byte[256];
        try {
            in.read(bArr);
            if (destFile.exists()) {
                new FileInputStream(destFile).read(bArr2);
            }
            in.close();
            return Arrays.equals(bArr, bArr2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static void pathExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static byte[] readByteArrFromFile(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        IOException e;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        break;
                    }
                }
                bufferedInputStream.close();
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return byteArrayOutputStream.toByteArray();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream2 = bufferedInputStream;
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                bufferedInputStream2 = bufferedInputStream;
                try {
                    e.printStackTrace();
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                    return byteArrayOutputStream.toByteArray();
                } catch (Throwable th3) {
                    th = th3;
                    bufferedInputStream2.close();
                    throw th;
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            bufferedInputStream = null;
            e.printStackTrace();
            bufferedInputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e9) {
            e = e9;
            e.printStackTrace();
            bufferedInputStream2.close();
            return byteArrayOutputStream.toByteArray();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readByteArrFromPath(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                return readByteArrFromFile(file);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                LogMgr.e(TAG, "fileName is not exist !");
            }
        }
        return null;
    }

    public static void saveLogFile(String log) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + System.currentTimeMillis() + ".txt");
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(log.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeBitmapToFile(String filePath, Bitmap bitmap) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            bitmap.recycle();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023 A[SYNTHETIC, Splitter:B:15:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0033 A[SYNTHETIC, Splitter:B:23:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040 A[SYNTHETIC, Splitter:B:30:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeDataToFile(String r3, byte[] r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x001c, IOException -> 0x002c, all -> 0x003c }
            r1.<init>(r0)     // Catch:{ FileNotFoundException -> 0x001c, IOException -> 0x002c, all -> 0x003c }
            r1.write(r4)     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x004b }
            r1.flush()     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x004b }
            if (r1 == 0) goto L_0x0016
            r1.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0016:
            return
        L_0x0017:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0016
        L_0x001c:
            r0 = move-exception
            r1 = r2
        L_0x001e:
            r0.printStackTrace()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0016
            r1.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0016
        L_0x0027:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0016
        L_0x002c:
            r0 = move-exception
            r1 = r2
        L_0x002e:
            r0.printStackTrace()
            if (r1 == 0) goto L_0x0016
            r1.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x0016
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0016
        L_0x003c:
            r0 = move-exception
            r1 = r2
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0043:
            throw r0
        L_0x0044:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0043
        L_0x0049:
            r0 = move-exception
            goto L_0x003e
        L_0x004b:
            r0 = move-exception
            goto L_0x002e
        L_0x004d:
            r0 = move-exception
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unisound.vui.common.file.FileHelper.writeDataToFile(java.lang.String, byte[]):void");
    }
}
