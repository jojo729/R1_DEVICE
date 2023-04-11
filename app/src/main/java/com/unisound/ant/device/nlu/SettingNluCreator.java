package com.unisound.ant.device.nlu;

import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.Result;
import nluparser.scheme.SName;
import nluparser.scheme.Semantic;
import nluparser.scheme.SettingCommonIntent;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;

public class SettingNluCreator {
    private static final int CMD_DECREASE = 2;
    private static final int CMD_INCREASE = 1;

    public static NLU<Intent, Result> createVolumeIncreaseNlu(String asrText) {
        return createNlu(asrText, 1);
    }

    public static NLU<Intent, Result> createVolumeDecreaseNlu(String asrText) {
        return createNlu(asrText, 2);
    }

    private static NLU<Intent, Result> createNlu(String asrText, int type) {
        NLU<Intent, Result> nlu = new NLU<>();
        nlu.setService(SName.SETTING_COMMON);
        SettingExtIntent intent = new SettingExtIntent();
        List<SettingIntent> operations = new ArrayList<>();
        SettingIntent operation = new SettingIntent();
        operation.setOperands(SettingCommonIntent.Operands_Common.ATTR_VOLUME);
        switch (type) {
            case 1:
                nlu.setText(asrText);
                operation.setOperator("ACT_INCREASE");
                break;
            case 2:
                nlu.setText(asrText);
                operation.setOperator("ACT_DECREASE");
                break;
        }
        operations.add(operation);
        intent.setOperations(operations);
        Semantic<Intent> semantic = new Semantic<>();
        semantic.setIntent(intent);
        nlu.setSemantic(semantic);
        return nlu;
    }
}
