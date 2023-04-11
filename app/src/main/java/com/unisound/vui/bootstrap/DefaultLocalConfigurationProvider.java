package com.unisound.vui.bootstrap;

import android.content.Context;
import com.unisound.vui.common.file.FileHelper;

public class DefaultLocalConfigurationProvider {
    private static final String GRAMMAR_DIR = "grammar";
    private static final String GRAMMAR_NAME = ".dat";
    private static final String JSGF_DIR = "jsgf_clg";
    private static final String JSGF_NAME = "_clg.dat";
    private static final String NLU_DIR = "nlu";
    private static final String NLU_NAME = "_nlu_offline.conf";
    private static final String TAG = "DefaultLocalConfigurationProvider";
    public static final String TTS_BACK_CHILD_NAME = "backend_tangtang_lpc2wav_22k_pf";
    public static final String TTS_BACK_LZL_NAME = "backend_lingling_lpc2wav_22k_pf";
    public static final String TTS_BACK_STANDAR_MALE_NAME = "backend_xiaofeng_lpc2wav_22k_pf";
    public static final String TTS_BACK_STANDAR_NAME = "backend_xiaowen_lpc2wav_22k_pf";
    public static final String TTS_BACK_SWEET_NAME = "backend_xuanxuan_lpc2wav_22k_pf";
    private static final String TTS_CUSTOM_DIC = "tts_custom_dic";
    private static final String TTS_DIR = "ttsmodel";
    private static final String TTS_FRONT_NAME = "frontend_model_offline";
    private static final String TTS_PCM = "tts_pcm";
    private Context context;

    public DefaultLocalConfigurationProvider(Context context2) {
        this.context = context2;
    }

    public void copyJsgfFromAssets() {
        if (!FileHelper.getGrammarDirectory(this.context).exists() && !FileHelper.copyDirectoryFromAssets(this.context, GRAMMAR_DIR, FileHelper.getGrammarDirectory(this.context))) {
            throw new RuntimeException("can't copy the grammar from assets");
        } else if (!FileHelper.copyDirectoryFromAssets(this.context, JSGF_DIR, FileHelper.getJSGFDirectory(this.context))) {
            throw new RuntimeException("can't copy the jsgf from assets");
        } else if (!FileHelper.getNluDirectory(this.context).exists() && !FileHelper.copyDirectoryFromAssets(this.context, NLU_DIR, FileHelper.getNluDirectory(this.context))) {
            throw new RuntimeException("can't copy the nlu from assets");
        }
    }

    public ANTELocalConfiguration getConfig() {
        ANTELocalConfiguration aNTELocalConfiguration = new ANTELocalConfiguration();
        String[] fileListFromAssets = FileHelper.getFileListFromAssets(this.context, NLU_DIR);
        int length = fileListFromAssets != null ? fileListFromAssets.length : 0;
        for (int i = 0; i < length; i++) {
            String str = fileListFromAssets[i];
            String substring = str.substring(0, str.lastIndexOf(NLU_NAME));
            ANTELocalConfiguration.LocalRecognition localRecognition = new ANTELocalConfiguration.LocalRecognition();
            localRecognition.setCompileJsgfPath(FileHelper.getJSGFFile(this.context, substring + JSGF_NAME).getAbsolutePath());
            localRecognition.setGrammerPath(FileHelper.getGrammarFile(this.context, substring + GRAMMAR_NAME).getAbsolutePath());
            localRecognition.setLocalNluPath(FileHelper.getNluFile(this.context, substring + NLU_NAME).getAbsolutePath());
            aNTELocalConfiguration.addConfiguration(substring, localRecognition);
        }
        return aNTELocalConfiguration;
    }

    public String getTTSBackendChildPath() {
        return FileHelper.getTTSModelFile(TTS_BACK_CHILD_NAME).getPath();
    }

    public String getTTSBackendLZLPath() {
        return FileHelper.getTTSModelFile(TTS_BACK_LZL_NAME).getPath();
    }

    public String getTTSBackendMalePath() {
        return FileHelper.getTTSModelFile(TTS_BACK_STANDAR_MALE_NAME).getPath();
    }

    public String getTTSBackendStandarPath() {
        return FileHelper.getTTSModelFile(TTS_BACK_STANDAR_NAME).getPath();
    }

    public String getTTSBackendSweetPath() {
        return FileHelper.getTTSModelFile(TTS_BACK_SWEET_NAME).getPath();
    }

    public String getTTSFrontendPath() {
        return FileHelper.getTTSModelFile(TTS_FRONT_NAME).getPath();
    }
}
