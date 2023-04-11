package com.unisound.vui.handler.session.music.network;

import com.unisound.ant.device.bean.AudioListResult;
import com.unisound.ant.device.bean.CurrentMusicList;
import com.unisound.ant.device.bean.RequestInfo;
import com.unisound.ant.device.netmodule.request.RequestBody;
import com.unisound.vui.handler.session.music.network.body.AudioBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MusicApi {
    @POST("appService")
    Call<AudioListResult> getAudio(@Body RequestBody<AudioBody> requestBody);

    @POST("audio/getAudioCurrentList")
    Call<AudioListResult> getCurrentAudioList(@Body RequestInfo requestInfo);

    @Headers({"Content-Type: application/json", "Accept:  application/json"})
    @HTTP(hasBody = true, method = "POST", path = "music/getCurrentList")
    Call<CurrentMusicList> getCurrentMusicList(@Body RequestInfo requestInfo);
}
