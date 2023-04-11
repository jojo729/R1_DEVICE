package com.unisound.ant.device.nlu;

import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.Intent;
import nluparser.scheme.NLU;
import nluparser.scheme.Operator;
import nluparser.scheme.Result;
import nluparser.scheme.SName;
import nluparser.scheme.Semantic;
import nluparser.scheme.SettingExtIntent;
import nluparser.scheme.SettingIntent;

public class MusicNluCreator {
    private static final int CMD_CLOSE = 5;
    private static final int CMD_NEXT = 4;
    private static final int CMD_PAUSE = 2;
    private static final int CMD_PLAY = 1;
    private static final int CMD_PREV = 3;
    private static final int CMD_STOP = 6;

    public static NLU<Intent, Result> createMusicPauseNlu(String asrText) {
        return createNlu(asrText, 2);
    }

    public static NLU<Intent, Result> createMusicStopNlu(String asrText) {
        return createNlu(asrText, 6);
    }

    public static NLU<Intent, Result> createMusicPlayNlu(String asrText) {
        return createNlu(asrText, 1);
    }

    public static NLU<Intent, Result> createMusicPrevNlu(String asrText) {
        return createNlu(asrText, 3);
    }

    public static NLU<Intent, Result> createMusicNextNlu(String asrText) {
        return createNlu(asrText, 4);
    }

    public static NLU<Intent, Result> createMusicCloseNlu(String asrText) {
        return createNlu(asrText, 5);
    }

    private static NLU<Intent, Result> createNlu(String asrText, int type) {
        NLU<Intent, Result> nlu = new NLU<>();
        nlu.setService(SName.SETTING_MP);
        SettingExtIntent intent = new SettingExtIntent();
        List<SettingIntent> operations = new ArrayList<>();
        SettingIntent operation = new SettingIntent();
        switch (type) {
            case 1:
                nlu.setText(asrText);
                operation.setOperator("ACT_PLAY");
                break;
            case 2:
                nlu.setText(asrText);
                operation.setOperator(Operator.ACT_PAUSE);
                break;
            case 3:
                nlu.setText(asrText);
                operation.setOperator(Operator.ACT_PREV);
                break;
            case 4:
                nlu.setText(asrText);
                operation.setOperator(Operator.ACT_NEXT);
                break;
            case 5:
                nlu.setText(asrText);
                operation.setOperator("ACT_CLOSE");
                break;
            case 6:
                nlu.setText(asrText);
                operation.setOperator(Operator.ACT_STOP);
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
