package com.unisound.vui.handler.session.music.playitem;

import java.util.ArrayList;
import java.util.List;
import nluparser.scheme.AudioResult;
import nluparser.scheme.BroadcastResult;
import nluparser.scheme.MusicResult;
import nluparser.scheme.NewsResult;

public final class PlayItemAdapter {
    public static List<PlayItem> adaptMusic(List<MusicResult.Music> musics) {
        ArrayList<PlayItem> playItems = new ArrayList<>(musics.size());
        for (MusicResult.Music audio : musics) {
            playItems.add(adapt(audio));
        }
        return playItems;
    }

    public static List<PlayItem> adaptAudio(List<AudioResult.Music> audios, String album, String artist) {
        ArrayList<PlayItem> playItems = new ArrayList<>(audios.size());
        for (AudioResult.Music audio : audios) {
            playItems.add(adapt(audio, album, artist));
        }
        return playItems;
    }

    public static List<PlayItem> adaptNews(List<NewsResult.NewsBean> news) {
        ArrayList<PlayItem> playItems = new ArrayList<>(news.size());
        for (NewsResult.NewsBean newItem : news) {
            playItems.add(adapt(newItem));
        }
        return playItems;
    }

    public static List<PlayItem> adaptBroadcast(BroadcastResult broadcastResult) {
        ArrayList<PlayItem> playItems = new ArrayList<>();
        playItems.add(adapt(broadcastResult));
        return playItems;
    }

    public static PlayItem adapt(NewsResult.NewsBean newsBean) {
        return new NewsItem(newsBean);
    }

    public static PlayItem adapt(BroadcastResult broadcastResult) {
        return new BroadcastItem(broadcastResult);
    }

    public static PlayItem adapt(MusicResult.Music music) {
        return new MusicItem(music);
    }

    public static PlayItem adapt(AudioResult.Music audio, String album, String artist) {
        return new AudioItem(audio, album, artist);
    }

    private PlayItemAdapter() {
    }
}
