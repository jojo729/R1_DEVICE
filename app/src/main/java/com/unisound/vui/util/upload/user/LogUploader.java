package com.unisound.vui.util.upload.user;

import com.unisound.vui.util.CrashHandler;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.upload.SimpleRequestListener;
import com.unisound.vui.util.upload.SimpleRequester;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class LogUploader implements Uploader {
    private static final String LOG_UPLOADER_TAG = "LogUploader";
    private static final String LOG_UPLOADER_URL = "http://10.200.19.108:8080/app_wx_adapt_service/m/uploadAppLogFile";
    private static final String TAG = "LogUploader";
    private List<File> crashFileList;
    private boolean isCrashFileExist = getCrashLogFile(CrashHandler.HPROF_FILE_PATH);
    private SimpleRequester requester = new SimpleRequester();
    private SimpleRequestListener simpleRequestListener = new SimpleRequestListener() {
        /* class com.unisound.vui.util.upload.user.LogUploader.AnonymousClass1 */

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onError(String errorMessage) {
            LogMgr.d("LogUploader", "onError : " + errorMessage);
            LogUploader.this.uploaderListener.onError(errorMessage);
        }

        @Override // com.unisound.vui.util.upload.SimpleRequestListener
        public void onResponse(String response) {
            LogMgr.d("LogUploader", "onResponse : " + response);
            LogUploader.this.deleteCrashFile();
            LogUploader.this.uploaderListener.onSuccess();
        }
    };
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private UploaderListener uploaderListener;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deleteCrashFile() {
        this.threadPool.submit(new Runnable() {
            /* class com.unisound.vui.util.upload.user.LogUploader.AnonymousClass2 */

            public void run() {
                FileUtils.deleteQuietly((File) LogUploader.this.crashFileList.get(0));
            }
        });
    }

    private boolean getCrashLogFile(String hprofFilePath) {
        File[] listFiles = new File(hprofFilePath).listFiles();
        if (listFiles.length == 0) {
            return false;
        }
        this.crashFileList = Arrays.asList(listFiles);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void renderByte(File crashFile, byte[] crashLogByte) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(crashFile);
        fileInputStream.read(new byte[fileInputStream.available()]);
    }

    public byte[] getCrashLogByte() {
        this.threadPool.submit(new Runnable() {
            /* class com.unisound.vui.util.upload.user.LogUploader.AnonymousClass3 */

            public void run() {
                try {
                    LogUploader.this.renderByte((File) LogUploader.this.crashFileList.get(0), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

    @Override // com.unisound.vui.util.upload.user.Uploader
    public void upload(JSONObject body, UploaderListener uploaderListener2) {
        if (this.isCrashFileExist) {
            this.uploaderListener = uploaderListener2;
            this.requester.request("LogUploader", LOG_UPLOADER_URL, getCrashLogByte(), this.simpleRequestListener);
            return;
        }
        LogMgr.d("LogUploader", "no crash file exist");
    }
}
