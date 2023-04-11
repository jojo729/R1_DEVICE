package com.unisound.ant.device.mqtt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.gson.Gson;
import com.unisound.ant.device.mqtt.bean.ChangeMessage;
import com.unisound.ant.device.mqtt.bean.ChannelParams;
import com.unisound.ant.device.mqtt.bean.ClientInfo;
import com.unisound.ant.device.mqtt.bean.MqttClientParam;
import com.unisound.ant.device.mqtt.bean.MqttConnection;
import com.unisound.ant.device.mqtt.bean.OnlineMessage;
import com.unisound.ant.device.mqtt.bean.RegisterParam;
import com.unisound.ant.device.mqtt.bean.ReportMessage;
import com.unisound.ant.device.service.BaseRequest;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.common.network.NetUtil;
import com.unisound.vui.util.AppGlobalConstant;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.UserPerferenceUtil;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttTransportChannel extends BaseTransportChannel implements Handler.Callback {

    private static MqttTransportChannel mqttTransportChannel;

    private static final int DEFAULT_REGISTER_DELAYED = 300;
//    private static final String INNER_ADDR = "://211.102.192.39:1883";
    private static final String INNER_ADDR = "://192.168.11.1:1883";

    private static final int MQTT_CONNECT_PREPARED = 0;
    private static final int MQTT_CONNECT_SUCCESS = 1;
    private static final int MQTT_RECEIVED_MSG = 5;
    private static final int MQTT_RECONNECT = 2;
    private static final String MQTT_REGISTER_OK = "mc_0008";
    private static final int MQTT_SEND_PARAM = 6;
    private static final String SP_NAME_FAILED_MESSAGES = "failed_msg";
    private static final String TAG = "MqttTransportChannel";
    private static String basePublish = "c2s/msg/";
    private static String baseSubscribe = "s2c/msg/";
    private static ChannelParams channelParams;
    public static MqttClientParam mqttParam;
    private static MqttAsyncClient client;
    private Handler connectHandler;
    private Context context = AppGlobalConstant.getContext();
    private boolean mConnecting;
    private RegisterParam mRegisterParam = new RegisterParam();
    private Runnable mRunnable = new Runnable() {
        /* class com.unisound.ant.device.mqtt.MqttTransportChannel.AnonymousClass4 */

        public void run() {
            openConectMqttTask();
        }
    };
    private ServerTask mServerTask;
    private SharedPreferences mSharedPrefs = this.context.getSharedPreferences(SP_NAME_FAILED_MESSAGES, 0);
    private OnMQTTStatusChangeListener phicommMQTTStatausChange;
    private int registerTimes = 0;
    private String regitstMqttErrorCode;
    private Handler taskHandler;
    private HandlerThread taskThread;

    synchronized static public  MqttTransportChannel getMqttTransportChannel(){
        if(mqttTransportChannel==null){
            mqttTransportChannel = new MqttTransportChannel();
        }
        return mqttTransportChannel;
    }


    @Override // com.unisound.ant.device.mqtt.BaseTransportChannel
    public void createChannel(Context context2, OnMQTTStatusChangeListener phicommMQTTStatausChange2) {
        this.phicommMQTTStatausChange = phicommMQTTStatausChange2;
        if (mqttParam == null) {
            this.connectHandler = new Handler(this);
            this.taskThread = new HandlerThread(TAG);
            this.taskThread.start();
            this.taskHandler = new Handler(this.taskThread.getLooper());
            queryMqttParamsAndConnect();
            return;
        }
        LogMgr.d(TAG, "server info is existed, connect to server directly");
        if (this.mConnecting) {
            LogMgr.d(TAG, "mqtt is connecting");
            return;
        }
        this.mConnecting = true;
        startConnect();
    }

    private void queryMqttParamsAndConnect() {
        if (client != null && client.isConnected()) {
            LogMgr.d(TAG, "mqtt is connected");
        } else if (this.mConnecting) {
            LogMgr.d(TAG, "mqtt is connecting");
        } else {
            this.mConnecting = true;
            this.registerTimes = 0;
            RegisterParam registerParam = setRegisterParam();
            Message message = Message.obtain();
            message.obj = registerParam;
            message.what = 6;
            this.connectHandler.sendMessage(message);
        }
    }

    @Override // com.unisound.ant.device.mqtt.BaseTransportChannel
    public void createChannel(Context context2, ChannelParams params, OnMQTTStatusChangeListener onMQTTStatusChangeListener) {
        if (params == null) {
            LogMgr.e(TAG, "createChannel param is null");
            return;
        }
        channelParams = params;
        createChannel(context2, onMQTTStatusChangeListener);
    }

    @Override // com.unisound.ant.device.mqtt.BaseTransportChannel
    public void sendData(final Object data) {
        if(true)return;
        if (channelParams != null && mqttParam != null) {
            ReportMessage message = new ReportMessage();
            message.setToken(channelParams.getToken());
            message.setMsg(data.toString());
            String content = new Gson().toJson(message);
            String topic = mqttParam.getPublish();
            LogMgr.d(TAG, "sendData, topic = " + topic + " data = " + content);
            sendMessage(topic, content.getBytes(), new IMqttActionListener() {
                /* class com.unisound.ant.device.mqtt.MqttTransportChannel.AnonymousClass1 */

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    LogMgr.d(MqttTransportChannel.TAG, "sendMessage Success");
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    LogMgr.e(MqttTransportChannel.TAG, "sendMessage Failure " + throwable.toString());
                    cacheFailedMessage(data);
                }
            });
        } else if (channelParams == null) {
            LogMgr.e(TAG, "sendData error : channelParams == null");
            cacheFailedMessage(data);
        } else {
            LogMgr.e(TAG, "sendData error : mqttParam == null, reconnecting mqtt");
            cacheFailedMessage(data);
            queryMqttParamsAndConnect();
        }
    }

    private String getMessageType(Object data) {
        if (data instanceof String) {
            try {
                return ((BaseRequest) new Gson().fromJson((String) data, BaseRequest.class)).getMessageType();
            } catch (Exception e) {
                LogMgr.e(TAG, "getMessageType, " + e);
            }
        }
        return null;
    }

    @Override // com.unisound.ant.device.mqtt.BaseTransportChannel
    public void receivedData(Object receivedMsg) {
        if (receivedMsg != null) {
            String msg = receivedMsg.toString();
            LogMgr.d(TAG, "receivedData message:" + msg);
            ChangeMessage responseMsg = null;
            try {
                responseMsg = (ChangeMessage) new Gson().fromJson(msg, ChangeMessage.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.listener != null && responseMsg != null) {
                this.listener.onReceivedMsg(1, responseMsg.getMsg());
            }
        }
    }

    @Override // com.unisound.ant.device.mqtt.BaseTransportChannel
    public void closeChannel() {
        disconnect();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean connect(MqttClientParam param) {
        if(true)return true;
        try {
            synchronized (MqttTransportChannel.class) {
                if (client == null)
                    client = new MqttAsyncClient(param.getConnectUrl(), param.getClientid(), null);
            }
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName(param.getUserName());
            options.setPassword(param.getPassWord().toCharArray());
            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(30);
            client.setCallback(new MqttCall());
            try {
                LogMgr.d(TAG, "mqtt connecting, params = " + param.toString());
                if(!client.isConnected()) client.connect(options).waitForCompletion();
                return true;
            } catch (Exception e) {
                if (e instanceof MqttException) {
                    this.mConnecting = false;
                    queryMqttParamsAndConnect();
                    LogMgr.e(TAG, "connect failure, errorCode = " + ((MqttException) e).getReasonCode());
                }
                LogMgr.e(TAG, "连接mqtt出错 : " + e.getMessage());
                return false;
            }
        } catch (Exception e2) {
            if (e2 instanceof MqttException) {
                this.mConnecting = false;
                queryMqttParamsAndConnect();
                LogMgr.e(TAG, "connect failure, errorCode = " + ((MqttException) e2).getReasonCode());
            }
            LogMgr.e(TAG, "连接mqtt出错 : " + e2.getMessage());
            return false;
        }
    }

    private void startConnect() {
        this.taskHandler.post(new Runnable() {
            /* class com.unisound.ant.device.mqtt.MqttTransportChannel.AnonymousClass2 */

            public void run() {
                if (connect(MqttTransportChannel.mqttParam)) {
                    subscriber(MqttTransportChannel.mqttParam.getSubscribe(), new int[]{2});
                    connectHandler.sendEmptyMessage(1);
                    return;
                }
                LogMgr.e(MqttTransportChannel.TAG, "start connect mqttServer is fail");
                onConnectFailure("mc_9999");
            }
        });
    }

    synchronized private  void reconnect() {
//        this.taskHandler.removeCallbacksAndMessages(null);
//        this.taskHandler.post(new Runnable() {
//            /* class com.unisound.ant.device.mqtt.MqttTransportChannel.AnonymousClass3 */
//
//            public void run() {
//                Log.d(MqttTransportChannel.TAG, "reconnect mqtt, isNetworkConnected = " + NetUtil.isNetworkConnected(context));
//                while (NetUtil.isNetworkConnected(context) && !mConnecting) {
//                    mConnecting = true;
//                    boolean isSuccess = connect(MqttTransportChannel.mqttParam);
//                    LogMgr.d(MqttTransportChannel.TAG, "reconnect isSuccess:" + isSuccess);
//                    if (isSuccess) {
//                        subscriber(MqttTransportChannel.mqttParam.getSubscribe(), new int[]{2});
//                        connectHandler.sendEmptyMessage(1);
//                        return;
//                    }
//                    mConnecting = false;
//                }
//            }
//
//        });
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public IMqttToken disconnect() {
        try {
            if (client != null && client.isConnected()) {
                LogMgr.d(TAG, "client disconnect mqtt");
                return client.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private IMqttToken sendMessage(String topic, byte[] message, IMqttActionListener listener) {
        try {
            if (client != null && client.isConnected()) {
                return client.publish(topic, message, 1, false, null, listener);
            }
            LogMgr.w(TAG, "mqtt未连接,重新连接mqtt");
            listener.onFailure(null, new Throwable("mqtt is not connected"));
            reconnect();
            return null;
        } catch (Exception e) {
            LogMgr.e(TAG, "sendMessage error : " + e.getMessage(), e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void cacheFailedMessage(Object data) {
        LogMgr.d(TAG, "cacheFailedMessage");
        if ("synInfo".equals(getMessageType(data))) {
            this.mSharedPrefs.edit().putString(String.valueOf(System.currentTimeMillis()), data.toString()).apply();
        }
    }

    private void resendFailedMessages() {
        Map<String, ?> failedMessages = this.mSharedPrefs.getAll();
        LogMgr.d(TAG, "resendFailedMessages, size:" + failedMessages.size());
        for (String key : failedMessages.keySet()) {
            String data = (String) failedMessages.get(key);
            if (data != null) {
                LogMgr.d(TAG, "resend ----> " + data);
                sendData(data);
            }
            this.mSharedPrefs.edit().remove(key).apply();
        }
    }

    public IMqttToken subscriber(String[] topics, int[] qos) {
        try {
            if (client != null) {
                LogMgr.d(TAG, "mqtt subscriber topics = " + Arrays.toString(topics));
                return client.subscribe(topics, qos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public IMqttToken unSubscriber(String[] topics) {
        try {
            if (client != null) {
                LogMgr.d(TAG, "mqtt unSubscriber topics = " + Arrays.toString(topics));
                return client.unsubscribe(topics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public class MqttCall implements MqttCallback {
        private MqttCall() {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void connectionLost(Throwable cause) {
            LogMgr.d(MqttTransportChannel.TAG, "connectionLost cause: " + cause.getMessage());
            UserPerferenceUtil.setDeviceOnlineState(context, false);
            connectHandler.sendEmptyMessage(2);
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void messageArrived(String topic, MqttMessage message) {
            String msg = new String(message.getPayload());
            LogMgr.d(MqttTransportChannel.TAG, "messageArrived topic:" + topic + ",message:" + msg);
            Message content = Message.obtain();
            content.what = 5;
            content.obj = msg;
            connectHandler.sendMessage(content);
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken token) {
            try {
                LogMgr.d(MqttTransportChannel.TAG, "deliveryComplete clientId = " + token.getClient().getClientId() + ", topic = " + Arrays.toString(token.getTopics()) + ", msgId = " + token.getMessageId() + " message:" + token.getMessage());
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public class ServerTask extends AsyncTask<Void, Void, Void> {
        private ServerTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            registerMqtt();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void registerMqtt() {
        com.unisound.ant.device.mqtt.bean.MqttMessage result = MqttMsgHelper.registerMqtt(this.mRegisterParam);
        LogMgr.d(TAG, "mqtt connection info : " + result);
        if (result == null || result.getResult() == null) {
            this.regitstMqttErrorCode = result == null ? "mc_9999" : result.getReturnCode();
            this.connectHandler.postDelayed(this.mRunnable, 300);
            return;
        }
        connectPrepared(result);
    }

    @Nullable
    private RegisterParam setRegisterParam() {
        LogMgr.d(TAG, "query mqtt connection info, params = " + channelParams.toString());
        RegisterParam registerParam = new RegisterParam();
        registerParam.setAppKey(channelParams.getAppKey());
        registerParam.setAppSecret(channelParams.getAppSecret());
        registerParam.setTcDeviceId(channelParams.getTcDeviceId());
        registerParam.setToken(channelParams.getToken());
        registerParam.setUdid(channelParams.getUdid());
        return registerParam;
    }

    private void sendOnlineMessage(boolean isOnline) {
        OnlineMessage message = new OnlineMessage();
        message.setClientId(mqttParam.getClientid());
        if (isOnline) {
            message.setEventType(1);
        } else {
            message.setEventType(2);
        }
        ClientInfo info = new ClientInfo();
        info.setUdid(channelParams.getUdid());
        info.setAppKey(channelParams.getAppKey());
        info.setPassportId(0);
        info.setSubsystemId(4);
        info.setExtras("");
        message.setClientInfo(info);
        sendData(new Gson().toJson(message));
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                LogMgr.d(TAG, "start conenct mqtt Server");
                startConnect();
                return false;
            case 1:
                UserPerferenceUtil.setDeviceOnlineState(this.context, true);
                sendOnlineMessage(true);
                if (this.listener != null) {
                    this.listener.onChannelConnected();
                }
                onConnectSuccess();
                LogMgr.d(TAG, "mqtt conenct is Success");
                return false;
            case 2:
                reconnect();
                return false;
            case 3:
            case 4:
            default:
                return false;
            case 5:
                receivedData(msg.obj);
                return false;
            case 6:
                this.mRegisterParam = (RegisterParam) msg.obj;
                this.connectHandler.postDelayed(this.mRunnable, 300);
                return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void openConectMqttTask() {
        this.registerTimes++;
        if (this.registerTimes <= 10) {
            LogMgr.d(TAG, "openConectMqttTask, registerTimes : " + this.registerTimes);
            if (this.mServerTask != null && !this.mServerTask.isCancelled()) {
                this.mServerTask.cancel(true);
                this.mServerTask = null;
            }
            this.mServerTask = new ServerTask();
            this.mServerTask.execute(new Void[0]);
            return;
        }
        onConnectFailure(this.regitstMqttErrorCode);
        this.connectHandler.removeCallbacks(this.mRunnable);
        LogMgr.w(TAG, "mqtt注册失败超出限制，不再重复注册");
    }

    private void onConnectSuccess() {
        this.mConnecting = false;
        this.phicommMQTTStatausChange.onSuccess();
        resendFailedMessages();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onConnectFailure(String regitstMqttErrorCode2) {
        this.mConnecting = false;
        this.phicommMQTTStatausChange.onFail(regitstMqttErrorCode2);
    }

    private void connectPrepared(com.unisound.ant.device.mqtt.bean.MqttMessage result) {
        this.connectHandler.removeCallbacks(this.mRunnable);
        MqttConnection connectionInfo = result.getResult().getConnection();
        String cliecntId = result.getResult().getClientId();
        mqttParam = new MqttClientParam();
        mqttParam.setUserName(connectionInfo.getUsername());
        mqttParam.setPassWord(connectionInfo.getPassword());
        mqttParam.setClientid(cliecntId);
        mqttParam.setSubscribe(new String[]{baseSubscribe + cliecntId});
        mqttParam.setPublish(basePublish + cliecntId);
        mqttParam.setConnectUrl(setEnvironmentParam(connectionInfo));
        LogMgr.d(TAG, "connect param:" + new Gson().toJson(mqttParam));
        this.connectHandler.sendEmptyMessage(0);
    }

    @NonNull
    private String setEnvironmentParam(MqttConnection connectionInfo) {
        if (ANTConfigPreference.isDev()) {
            return connectionInfo.getProtocol() + INNER_ADDR;
        }
        return connectionInfo.getProtocol() + "://" + connectionInfo.getIp() + ":" + connectionInfo.getPort();
    }
}
