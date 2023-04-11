package com.unisound.vui.data.tts;

import android.content.Context;
import androidx.annotation.NonNull;
import com.phicomm.speaker.device.R;

import java.util.*;

public class a {
    protected Context mContext;
    protected Map<String, String> ttsContentMap = new HashMap();

    public a(Context context) {
        this.mContext = context;
    }

    @NonNull
    private List<String> getGoodMorningList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("tts_sweet_good_morning_50_1.wav");
        arrayList.add("tts_sweet_good_morning_50_2.wav");
        arrayList.add("tts_sweet_good_morning_50_3.wav");
        return arrayList;
    }

    @NonNull
    private List<String> getGoodNightList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("tts_sweet_good_night_49_1.wav");
        arrayList.add("tts_sweet_good_night_49_2.wav");
        return arrayList;
    }

    private String getRandomWord(List<String> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public void init() {
        List<String> goodNightList = getGoodNightList();
        List<String> goodMorningList = getGoodMorningList();
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_start_dormant), "tts_sweet_start_dormant_12.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_cancel_collect), "tts_sweet_cancle_25.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_add_note_tip), "tts_sweet_check_on_app_48.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_collect), "tts_sweet_collected_24.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_open_bluetooth), "tts_sweet_connect_ble_15.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_close_bluetooth), "tts_sweet_disconnected_ble_16.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_stop_match_net), "tts_sweet_finish_net_20.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_cancel), "tts_sweet_good_bye_51.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_grow_up), "tts_sweet_grow_up.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_adjust_volume_increase), "tts_sweet_increase_32.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_not_listener_clear), "tts_sweet_listener_not_clear_05.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_net_is_weak), "tts_sweet_net_error_8.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_cant_find_content), "tts_sweet_not_content_58.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_is_already_playing), "tts_sweet_play_for_you_27.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_say_again), "tts_sweet_say_again_6.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_open_bluetooth_airplay), "tts_sweet_start_connect_ble_about_13.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_start_match_net), "tts_sweet_start_net_19.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_mode_all_repeat), "tts_sweet_switch_listorder_30.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_mode_shuffle), "tts_sweet_switch_shuffle_29.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_mode_repeat_once), "tts_sweet_switch_single_28.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_cant_help_you), "tts_sweet_unspport_cant_help_you.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_change_no_supported), "tts_sweet_unsupport_function_7.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_i_cant), "tts_sweet_unsupport_unable_2.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_adjust_volume_sorry_max), "tts_sweet_volume_is_max_34.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_adjust_volume_sorry_min), "tts_sweet_volume_is_min_35.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_adjust_volume_decrease), "tts_sweet_decrease_33.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_music_find_song_not_match), "tts_sweet_no_music_57.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_start_upgrade), "tts_sweet_start_update_22.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_upgrade_completed), "tts_sweet_update_completed_23.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_not_collected_content), "tts_sweet_no_collect_content_31.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_want_to_music), "tts_sweet_music_advice_56.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_what_song_listener), "tts_sweet_music_wish_55.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_need_call_me), "tts_sweet_need_call_me_52.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_what_do_for_you), "tts_sweet_help_for_you_54.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_enjoy_please), "tts_sweet_enjoy_26.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_exit_match_net), "tts_sweet_exit_net_21.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_good_morning), getRandomWord(goodMorningList));
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_good_night), getRandomWord(goodNightList));
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_close_ble), "tts_sweet_close_ble_17.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_open_bluetooth_airplay), "tts_sweet_close_ble_about_14.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_not_bind), "tts_sweet_bind_notice_59.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_memo_set_result_withouttime_exception), "tts_sweet_what_alrm_time_39.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_what_notice), "tts_sweet_what_notice_42.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_what_time_notice), "tts_sweet_what_time_notice_41.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_record_what), "tts_sweet_record_what_47.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_note_detail_info), "tts_sweet_note_detail_info_46.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_notice_detail), "tts_sweet_notice_detail_43.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_reminder_count_max), "tts_sweet_notice_max_37.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_bootloader_welcome), "tts_sweet_first_word.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_countdown_detail), "tts_sweet_countdown_detail_45.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_count_down_count_max), "tts_sweet_countdown_max_38.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_countdown_time), "tts_sweet_countdown_time_44.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_alarm_count_max), "tts_sweet_alarm_max_36.wav");
        this.ttsContentMap.put(this.mContext.getString(R.string.tts_sweet_alarm_time), "tts_sweet_alarm_time_40.wav");
    }
}
