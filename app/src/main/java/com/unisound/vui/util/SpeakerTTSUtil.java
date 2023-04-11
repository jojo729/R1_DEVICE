package com.unisound.vui.util;

import android.content.Context;
import java.util.ArrayList;

import com.phicomm.speaker.device.R;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SpeakerTTSUtil {
    public static final int TTS_SPEAKER_DEFAULT = 0;
    public static final int TTS_SPEAKER_FEMALE = 2;
    public static final int TTS_SPEAKER_MALE = 1;
    public static ArrayList<Integer> arrayString = new ArrayList<>();
    private static int speakerType = 0;

    static {
        arrayString.add(Integer.valueOf(R.array.tts_weather_no_result));
        arrayString.add(Integer.valueOf(R.array.tts_unsupport_answer));
    }

    private SpeakerTTSUtil() {
    }

    private static String getRadom(String s) {
        String[] split = s.split(MqttTopic.MULTI_LEVEL_WILDCARD);
        return split.length > 1 ? split[(int) (Math.random() * ((double) split.length))] : split[0];
    }

    private static String getSpeakerTTSArray(int id, int speaker, Context context) {
        String[] stringArray = context.getResources().getStringArray(id);
        if (stringArray != null) {
            switch (speakerType) {
                case 0:
                    if (stringArray.length > 0) {
                        return getRadom(stringArray[0]);
                    }
                    break;
                case 1:
                    if (stringArray.length > 1) {
                        return getRadom(stringArray[1]);
                    }
                    break;
                case 2:
                    if (stringArray.length > 2) {
                        return getRadom(stringArray[2]);
                    }
                    break;
            }
        }
        return null;
    }

    private static String getSpeakerTTSString(int id, Context context) {
        return context.getResources().getString(id);
    }

    public static String getTTSString(int id, int speaker, Context context) {
        return arrayString.contains(Integer.valueOf(id)) ? getSpeakerTTSArray(id, speaker, context) : getSpeakerTTSString(id, context);
    }

    public static String[] getTTSStringArray(int id, Context context) {
        return context.getResources().getStringArray(id);
    }

    public static String getTTSStringFromArray(int id, Context context) {
        String[] tTSStringArray = getTTSStringArray(id, context);
        String[] split = tTSStringArray[(int) (Math.random() * ((double) tTSStringArray.length))].split(MqttTopic.MULTI_LEVEL_WILDCARD);
        return split.length > speakerType ? split[speakerType] : split[0];
    }

    public static void setSpeakerTTSType(Context context, String type) {
        boolean enableRecognizePersonal = UserPerferenceUtil.getEnableRecognizePersonal(context);
        LogMgr.d("--enable " + enableRecognizePersonal);
        if (!enableRecognizePersonal) {
            speakerType = 0;
        } else if ("male".equals(type)) {
            speakerType = 1;
        } else if ("female".equals(type)) {
            speakerType = 2;
        }
    }
}
