package com.unisound.vui.common.media;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import okhttp3.CacheControl;
import okhttp3.Call;

public final class OkHttpDataSourceFactory extends HttpDataSource.BaseFactory {
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    private final TransferListener<? super DataSource> listener;
    private final String userAgent;

    public OkHttpDataSourceFactory(Call.Factory callFactory2, String userAgent2, TransferListener<? super DataSource> listener2) {
        this(callFactory2, userAgent2, listener2, null);
    }

    public OkHttpDataSourceFactory(Call.Factory callFactory2, String userAgent2, TransferListener<? super DataSource> listener2, CacheControl cacheControl2) {
        this.callFactory = callFactory2;
        this.userAgent = userAgent2;
        this.listener = listener2;
        this.cacheControl = cacheControl2;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.upstream.HttpDataSource.BaseFactory
    public OkHttpDataSource createDataSourceInternal(HttpDataSource.RequestProperties defaultRequestProperties) {
        return new OkHttpDataSource(this.callFactory, this.userAgent, null, this.listener, this.cacheControl, defaultRequestProperties);
    }
}
