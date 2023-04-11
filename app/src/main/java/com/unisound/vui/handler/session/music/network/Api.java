package com.unisound.vui.handler.session.music.network;

import com.unisound.vui.common.config.ANTConfigPreference;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String BASE_URL = ANTConfigPreference.getAppServerUrl();
    private static Retrofit sRetrofit;

    public static MusicApi getMusicApi() {
        createRetrofit();
        return (MusicApi) sRetrofit.create(MusicApi.class);
    }

    private static void createRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
    }
}
