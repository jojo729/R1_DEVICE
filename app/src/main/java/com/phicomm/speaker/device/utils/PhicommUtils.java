package com.phicomm.speaker.device.utils;

import android.util.Log;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unisound.vui.util.HttpUtils;
import com.unisound.vui.util.LogMgr;
import nluparser.scheme.SName;
import okhttp3.Response;
import xyz.yhsj.kmusic.KMusic;
import xyz.yhsj.kmusic.entity.MusicResp;
import xyz.yhsj.kmusic.entity.MusicTop;
import xyz.yhsj.kmusic.entity.Song;
import xyz.yhsj.kmusic.impl.QQImpl;
import xyz.yhsj.kmusic.site.MusicSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PH
 */
public class PhicommUtils {

    public static JSONObject refomatNewApi(String word, String response) {

        LogMgr.d("PPP HOOK", "New API NLU Result: " + response);
        JSONObject mixtureJson = new JSONObject();
        JSONArray net_nlus = new JSONArray();
        mixtureJson.put("net_nlu", net_nlus);
        JSONObject resJson = JSONObject.parseObject(response);
        net_nlus.add(resJson);

        String service = resJson.getString("service");

        // service -> cn.yunzhisheng.news -> news
        // service -> cn.yunzhisheng.music -> audioList
        // history -> cn.yunzhisheng.weather ->
//        String hook_res = "{'net_nlu':[{'semantic':{'intent':{'language':'zh','tag':'经典','keyword':'华语 经典'}},'code':'SEARCH_CATEGORY','data':{'result':{'total':'1','playlist':[{'url_m4a_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2nhffgcABuIua2Cpis670.m4a','episode':7,'urlm4a':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/71/wKgMeF3Tj2jzjdjeAAqJXTadZ1U342.m4a','play_count':1286527,'title':'New Boy-朴树 （乐夏掀起的金曲记忆，怀念着我们的青春）','url':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2iC9TWdAA2ba79Wpww108.mp3','tags':'怀旧,经典,老歌','cover':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=100&rows=100','duration':222,'update_time':'2020-03-13 16:06:06','url_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2rir4yiABsxVb9Lyos697.mp3','id':229666914,'cover_large':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=640&rows=640'}],'totalTime':3,'pagesize':'30','errorCode':0,'page':'1','source':1,'dataSourceName':'喜马拉雅'}},'originIntent':{'nluSlotInfos':[]},'history':'cn.yunzhisheng.audio','source':'nlu','uniCarRet':{'result':{},'returnCode':609,'message':'aios-home.hivoice.cn'},'rc':0,'general':{'actionAble':'true','quitDialog':'true','text':'为您播放经典:','type':'T'},'returnCode':0,'audioUrl':'http://asrv3.hivoice.cn/trafficRouter/r/l9P4z9','service':'cn.yunzhisheng.audio','nluProcessTime':'121','text':'播放华语经典','responseId':'3e0aab8a02924756b1430c2db0171963'}]}";

        switch (service) {
            case SName.NEWS: {
                return mixtureJson;
            }
            case SName.MUSIC: {
                JSONObject jResult = resJson.getJSONObject("data").getJSONObject("result");
//                if("random".equals(jResult.getString("searchType"))){
//                    LogMgr.w("PPP HOOK:","FOUND RANDOM Type, May Not Found");
                // research by kw music api
                String keyword = resJson.getJSONObject("semantic").getJSONObject("intent").getString("keyword");
                if (word.contains("播放音乐")) {
                    keyword = word.replace("播放音乐", "");
                }
                LogMgr.d("PPP HOOK", "Search Music By KW : "+keyword);
                JSONArray kwSongList = searchSongByKWMusic(keyword);
                jResult.put("audioList", kwSongList);
                jResult.put("searchType", "common");

//                }

                jResult.put("musicinfo", jResult.remove("audioList"));
                return mixtureJson;
            }
            case SName.AUDIO: {
                JSONObject jResult = resJson.getJSONObject("data").getJSONObject("result");
                jResult.put("playlist", jResult.remove("audioList"));
                return mixtureJson;
            }
            case SName.WEATHER: {
                return mixtureJson;
            }
            case SName.SETTING: {
                String text = resJson.getString("text");
                JSONObject operation = resJson.getJSONObject("semantic").getJSONObject("intent").getJSONArray("operations").getJSONObject(0);
                if (text.contains("氛围灯")) {
                    operation.put("operands", "AmbientLight");
                }

                return mixtureJson;
            }
            case SName.CHAT: {
                resJson.remove("displayProcessRecord");
                return mixtureJson;
            }
            default: {
                return mixtureJson;
            }
        }
    }


    public static String asrByWord(String word) throws IOException {
        String url = "http://106.14.226.237:8080/service/iss-test?platform=&screen=&text=";
        String url2 = "&appkey=3dcddlnx7ddlb2xatjxtbtxha6xah7iogajzkqie&scenario=child&filterName=search&ver=3.0&udid=d1cee3ae6ff46&returnType=json&appsig=A6702357B7904B43907E7803665FBD5FE08C57A5&appver=1.0.1&city=%E6%B7%B1%E5%9C%B3&history=&time=&voiceid=8AAAD808EDF04697AF3C74C22DC4CF0E&gps=&method=iss.getTalk&dpi=&viewId=";

        Response response = HttpUtils.getInstance().getSync(url + word + url2);

        return response.body().string();
    }

    public static String byNewApi(String word) throws IOException {
        return refomatNewApi(word, asrByWord(word)).toString();
    }

    public static String hookRawAsr(String rawAsr) {
        // var2 为ARS识别结果,已经包含了结果

        // {"semantic":{"intent":{}},"code":"SEARCH_RANDOM","originIntent":{"nluSlotInfos":[]},"history":"cn.yunzhisheng.music","source":"nlu","uniCarRet":{"result":{},"returnCode":609,"message":"aios-home.hivoice.cn"},"asr_recongize":"播放音乐。","rc":0,"returnCode":0,"audioUrl":"http://asrv3.hivoice.cn/trafficRouter/r/zjzOH7","retTag":"nlu","service":"cn.yunzhisheng.music","nluProcessTime":"301","taskName":"search","text":"播放音乐","responseId":"2494b7b222794068a70cb4057bfdbd8c"}
        // hook
        Log.d("PPP", "old asr raw res: " + rawAsr);
        JSONObject resJson = JSONObject.parseObject(rawAsr);
        String asrWord = resJson.getString("text");
        Log.d("PPP", "asr word: " + asrWord);

        if (asrWord != null) {
            boolean need = true;
            switch (resJson.getString("service")) {
                case SName.NEWS: {
                    break;
                }
                case SName.MUSIC: {
                    break;
                }
                case SName.AUDIO: {
                    break;
                }
                case SName.WEATHER: {
                    break;
                }
                case SName.SETTING: {
                    need = false;
                    String text = resJson.getString("text");
                    if (text.contains("氛围灯")) {
                        Log.d("PPP", "hook old api light: " + asrWord);
                        JSONObject operation = resJson.getJSONObject("semantic").getJSONObject("intent").getJSONArray("operations").getJSONObject(0);
                        operation.put("operands", "AmbientLight");
                        rawAsr = resJson.toJSONString();
                    }
                    break;

                }
                case SName.SETTING_COMMON: {
                    need = false;
                    break;
                }
                case SName.ALARM:{
                    need=false;
                    break;
                }
                case SName.CALENDAR:{
                    need=false;
                    break;
                }
                case SName.REMINDER:{
                    need = false;
                    break;
                }
                case SName.CHAT: {
                    break;
                }
            }
            if (need) {
                JSONObject data = resJson.getJSONObject("data");

                // audioUrl 为用户语音跟踪信息，可以判断是否为最终结果
                String audioUrl = resJson.getString("audioUrl");
                if (data == null && audioUrl!=null) {
                    try {
                        rawAsr = byNewApiRaw(asrWord);
                        Log.d("PPP", "new hook api asr data: " + rawAsr);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

        }
        return rawAsr;
    }

    public static String byNewApiRaw(String word) throws IOException {
        JSONObject res = refomatNewApi(word, asrByWord(word)).getJSONArray("net_nlu").getJSONObject(0);
        res.put("asr_recongize", word + "。");
        return res.toJSONString();
    }


    public static JSONArray searchSongByKWMusic(String key) {
        MusicResp<List<Song>> tops = KMusic.search(key, 1, 5, MusicSite.QQ);
        JSONArray playlist = new JSONArray();
        if (tops.getData() == null) {
            return playlist;
        }
        for (Song song : tops.getData()) {
            com.alibaba.fastjson.JSONObject jsonSong = new com.alibaba.fastjson.JSONObject();
            // 播报 title artlist
            jsonSong.put("title", song.getTitle());
            jsonSong.put("artist", song.getAuthor());

            jsonSong.put("audioType", 1);
            jsonSong.put("domainName", "music");
            jsonSong.put("resourceType", 2);
            jsonSong.put("episode", song.getCode());
            jsonSong.put("play_count", 10000);
            jsonSong.put("url", song.getUrl());
            jsonSong.put("tags", "怀旧,经典,老歌");
//            jsonSong.put("cover", song.getPic());
            jsonSong.put("duration", 312);
            jsonSong.put("id", song.getSongid());
//            jsonSong.put("update_time", "2020-03-13 16:06:06");
//            jsonSong.put("url_high", song.getUrl());
//            jsonSong.put("cover_large", song.getPic());
            playlist.add(jsonSong);
        }
        return playlist;
    }


    public static String byKWMusic(String word) {
        // 播放音乐
        if (word.contains("播放音乐")) {

            String hook_res = "{'net_nlu':[{'semantic':{'intent':{'language':'zh','tag':'经典','keyword':'华语 经典'}},'code':'SEARCH_CATEGORY','data':{'result':{'total':'1','playlist':[{'url_m4a_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2nhffgcABuIua2Cpis670.m4a','episode':7,'urlm4a':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/71/wKgMeF3Tj2jzjdjeAAqJXTadZ1U342.m4a','play_count':1286527,'title':'New Boy-朴树 （乐夏掀起的金曲记忆，怀念着我们的青春）','url':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2iC9TWdAA2ba79Wpww108.mp3','tags':'怀旧,经典,老歌','cover':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=100&rows=100','duration':222,'update_time':'2020-03-13 16:06:06','url_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2rir4yiABsxVb9Lyos697.mp3','id':229666914,'cover_large':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=640&rows=640'}],'totalTime':3,'pagesize':'30','errorCode':0,'page':'1','source':1,'dataSourceName':'喜马拉雅'}},'originIntent':{'nluSlotInfos':[]},'history':'cn.yunzhisheng.audio','source':'nlu','uniCarRet':{'result':{},'returnCode':609,'message':'aios-home.hivoice.cn'},'rc':0,'general':{'actionAble':'true','quitDialog':'true','text':'为您播放经典:','type':'T'},'returnCode':0,'audioUrl':'http://asrv3.hivoice.cn/trafficRouter/r/l9P4z9','service':'cn.yunzhisheng.audio','nluProcessTime':'121','text':'播放华语经典','responseId':'3e0aab8a02924756b1430c2db0171963'}]}";

            JSONObject tempJsonObject = JSONObject.parseObject(hook_res);

            List<Song> songs = new ArrayList<>();
            if (word.startsWith("播放音乐。")) {
                for (MusicTop musicTop : QQImpl.INSTANCE.getSongTop().getData()) {
                    MusicResp<List<Song>> musicResp = QQImpl.INSTANCE.getSongTopDetail(musicTop.getTopId(), musicTop.getTopType(), musicTop.getTopKey(), 1, 100);
                    if (musicResp.getData().size() > 0) {
                        songs = musicResp.getData();
                        break;
                    }

                }

            } else {
                MusicResp<List<Song>> tops = KMusic.search(word.replace("播放音乐", ""), 1, 10, MusicSite.QQ);
                songs = tops.getData();
            }


            JSONArray playlist = new JSONArray();
            for (Song song : songs) {
                com.alibaba.fastjson.JSONObject jsonSong = new com.alibaba.fastjson.JSONObject();
                jsonSong.put("url_m4a_high", song.getUrl());
                jsonSong.put("episode", song.getCode());
                jsonSong.put("urlm4a", song.getUrl());
                jsonSong.put("play_count", 10000);
                jsonSong.put("title", song.getTitle());
                jsonSong.put("url", song.getUrl());
                jsonSong.put("tags", "怀旧,经典,老歌");
                jsonSong.put("cover", song.getPic());
                jsonSong.put("duration", 312);
                jsonSong.put("id", song.getSongid());
                jsonSong.put("update_time", "2020-03-13 16:06:06");
                jsonSong.put("url_high", song.getUrl());
                jsonSong.put("cover_large", song.getPic());
                playlist.add(jsonSong);
            }
            if (playlist.size() > 0) {
                com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();
                jsonResult.put("total", playlist.size());
                jsonResult.put("totalTime", 3);
                jsonResult.put("pagesize", 10);
                jsonResult.put("errorCode", 0);
                jsonResult.put("page", "1");
                jsonResult.put("source", 1);
                jsonResult.put("dataSourceName", "QQ");
                jsonResult.put("playlist", playlist);

                com.alibaba.fastjson.JSONObject res1 = tempJsonObject.getJSONArray("net_nlu").getJSONObject(0);
                res1.getJSONObject("data").put("result", jsonResult);
                res1.getJSONObject("general").put("text", word);
//                    String hook_res = "{'net_nlu':[{'semantic':{'intent':{'language':'zh','tag':'经典','keyword':'华语 经典'}},'code':'SEARCH_CATEGORY','data':{'result':{'total':'1','playlist':[{'url_m4a_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2nhffgcABuIua2Cpis670.m4a','episode':7,'urlm4a':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/71/wKgMeF3Tj2jzjdjeAAqJXTadZ1U342.m4a','play_count':1286527,'title':'New Boy-朴树 （乐夏掀起的金曲记忆，怀念着我们的青春）','url':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2iC9TWdAA2ba79Wpww108.mp3','tags':'怀旧,经典,老歌','cover':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=100&rows=100','duration':222,'update_time':'2020-03-13 16:06:06','url_high':'http://aod.cos.tx.xmcdn.com/group68/M04/D0/3D/wKgMbl3Tj2rir4yiABsxVb9Lyos697.mp3','id':229666914,'cover_large':'http://imgopen.xmcdn.com/group49/M09/16/CF/wKgKl1vtNNfCAyFWAAITMtQComg191.jpg!op_type=3&columns=640&rows=640'}],'totalTime':3,'pagesize':'30','errorCode':0,'page':'1','source':1,'dataSourceName':'喜马拉雅'}},'originIntent':{'nluSlotInfos':[]},'history':'cn.yunzhisheng.audio','source':'nlu','uniCarRet':{'result':{},'returnCode':609,'message':'aios-home.hivoice.cn'},'rc':0,'general':{'actionAble':'true','quitDialog':'true','text':'为您播放经典:','type':'T'},'returnCode':0,'audioUrl':'http://asrv3.hivoice.cn/trafficRouter/r/l9P4z9','service':'cn.yunzhisheng.audio','nluProcessTime':'121','text':'播放华语经典','responseId':'3e0aab8a02924756b1430c2db0171963'}]}";
//                    hook_res = hook_res.replace('\'','"');
                return tempJsonObject.toJSONString();
            }
        }
        return null;
    }
}