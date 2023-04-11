package com.unisound.vui.handler.session.music.syncloud;

import com.unisound.ant.device.netmodule.request.RequestBody;
import com.unisound.vui.common.config.ANTConfigPreference;
import com.unisound.vui.handler.session.music.playitem.PlayItem;
import com.unisound.vui.handler.session.music.syncloud.SyncMusicListBean;
import com.unisound.vui.util.HttpUtils;
import com.unisound.vui.util.JsonTool;
import com.unisound.vui.util.LogMgr;
import com.unisound.vui.util.ThreadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MusicHttpUtils {
    public static final String BUSINESS_TYPE_MUSIC = "music";
    public static final String CMD_UPLOAD_MUSIC_LIST = "uploadMusicList";
    public static final String PROTOCOL_VERSION = "2.0.0";
    public static final int RESPONSE_STATUS_SUCCESS = 200;
    private static final String TAG = MusicHttpUtils.class.getSimpleName();

    public static void uploadMusicList(final String udid, final List<PlayItem> playItemList) {
        ThreadUtils.executeInSingle(new Runnable() {
            /* class com.unisound.vui.handler.session.music.syncloud.MusicHttpUtils.AnonymousClass1 */

            public void run() {
                RequestBody<SyncMusicListBean> body = new RequestBody<>();
                body.setBusinessType("music");
                body.setCommand(MusicHttpUtils.CMD_UPLOAD_MUSIC_LIST);
                body.setVersion("2.0.0");
                body.setTcl(new RequestBody.ClientInfo());
                SyncMusicListBean musicListBean = new SyncMusicListBean();
                musicListBean.setUdid(udid);
                List<SyncMusicListBean.MusicInfo> musicList = new ArrayList<>();
                for (PlayItem item : playItemList) {
                    SyncMusicListBean.MusicInfo musicInfo = new SyncMusicListBean.MusicInfo();
                    musicInfo.setId(item.getId());
                    musicInfo.setMusicListId(item.getListId());
                    musicInfo.setTitle(item.getTitle());
                    musicInfo.setArtist(item.getArtist());
                    musicInfo.setAlbum(item.getAlbum());
                    musicInfo.setUrl(item.getUrl());
                    musicInfo.setDuration(item.getDuration());
                    musicInfo.setImgUrl(item.getImgUrl());
                    musicInfo.setHdImgUrl(item.getHdImgUrl());
                    musicInfo.setmLyric(item.getmLyric());
                    musicInfo.setCollected(item.isCollected());
                    musicInfo.setErrorCode(0);
                    musicList.add(musicInfo);
                }
                musicListBean.setMusicList(musicList);
                body.setData(musicListBean);
                String url = ANTConfigPreference.getAppServerUrl() + "appService";
                String dataString = JsonTool.toJson(body);
                LogMgr.d(MusicHttpUtils.TAG, "uploadMusicList, url = " + url + ", params = " + dataString);
                Response response = HttpUtils.getInstance().postSync(url, dataString);
                if (HttpUtils.isResponseCorrect(response)) {
                    try {
                        JSONObject jsonObject = JsonTool.parseToJSONObject(response.body().string());
                        int status = jsonObject.getInt("status");
                        if (200 == status) {
                            LogMgr.d(MusicHttpUtils.TAG, "uploadMusicList success!");
                        } else {
                            LogMgr.d(MusicHttpUtils.TAG, "uploadMusicList failure, error code = " + status + ", message = " + jsonObject.getString("detailInfo"));
                        }
                    } catch (IOException e) {
                        LogMgr.e(MusicHttpUtils.TAG, "uploadMusicList error, " + e.getMessage());
                    } catch (JSONException e2) {
                        LogMgr.e(MusicHttpUtils.TAG, "uploadMusicList error, " + e2.getMessage());
                    }
                } else {
                    LogMgr.d(MusicHttpUtils.TAG, new StringBuilder().append("uploadMusicList failure, ").append(response == null ? "response is null!" : " response code = " + response.code()).toString());
                }
            }
        });
    }
}
