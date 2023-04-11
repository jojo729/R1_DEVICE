package com.phicomm.speaker.device.ui;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.baidu.mobstat.StatService;
import com.phicomm.speaker.device.R;
import com.unisound.vui.util.LogMgr;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMgr.d(TAG, "onCreate");
        setContentView(R.layout.layout_welcome);

        ButterKnife.bind(this);

        StatService.setDebugOn(false);
        StatService.setAppKey("efe5be0e5e");
        StatService.setAppChannel(this,"tdre",true);
        StatService.setOn(this, 1);
        StatService.setSessionTimeOut(30);
        StatService.enableDeviceMac(this, true);
        StatService.setForTv(this, true) ;
        StatService.autoTrace(this, true, false);

        
        
        
    }

    public void onResume() {
        super.onResume();
        LogMgr.d(TAG, "onResume");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LogMgr.d(TAG, "--->>onDestroy");
        super.onDestroy();
    }
}
