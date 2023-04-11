package com.unisound.vui.bootstrap;

import android.content.Context;
import com.unisound.vui.util.LocalizeDataUtil;
import com.unisound.vui.util.UserPerferenceUtil;
import com.unisound.vui.util.entity.VocabSlotTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultANTEMainVocabProvider {
    public Map<String, List<String>> getMainVocab(Context context) {
        HashMap hashMap = new HashMap();
        List<String> userDef = LocalizeDataUtil.newInstance(context).getUserDef();
        if (userDef.size() > 0) {
            hashMap.put(VocabSlotTag.Domain_userdef_commands_slot.toString(), userDef);
        }
        if (!UserPerferenceUtil.getOneshotEnable(context)) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(UserPerferenceUtil.getMainWakeupWord(context));
            if (arrayList.size() > 0) {
                hashMap.put(VocabSlotTag.Domain_wakeup_words_slot.toString(), arrayList);
            }
        }
        return hashMap;
    }
}
