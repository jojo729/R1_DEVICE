package com.unisound.vui.common.media;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Predicate;
import okhttp3.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class OkHttpDataSource implements HttpDataSource {
    private static final AtomicReference<byte[]> skipBufferReference = new AtomicReference<>();
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    private final Predicate<String> contentTypePredicate;
    private DataSpec dataSpec;
    private final RequestProperties defaultRequestProperties;
    private final TransferListener<? super OkHttpDataSource> listener;
    private boolean opened;
    private final RequestProperties requestProperties;
    private Response response;
    private InputStream responseByteStream;
    private final String userAgent;

    public OkHttpDataSource(Call.Factory callFactory2, String userAgent2, Predicate<String> contentTypePredicate2) {
        this(callFactory2, userAgent2, contentTypePredicate2, null);
    }

    public OkHttpDataSource(Call.Factory callFactory2, String userAgent2, Predicate<String> contentTypePredicate2, TransferListener<? super OkHttpDataSource> listener2) {
        this(callFactory2, userAgent2, contentTypePredicate2, listener2, null, null);
    }

    public OkHttpDataSource(Call.Factory callFactory2, String userAgent2, Predicate<String> contentTypePredicate2, TransferListener<? super OkHttpDataSource> listener2, CacheControl cacheControl2, RequestProperties defaultRequestProperties2) {
        this.callFactory = (Call.Factory) Assertions.checkNotNull(callFactory2);
        this.userAgent = Assertions.checkNotEmpty(userAgent2);
        this.contentTypePredicate = contentTypePredicate2;
        this.listener = listener2;
        this.cacheControl = cacheControl2;
        this.defaultRequestProperties = defaultRequestProperties2;
        this.requestProperties = new RequestProperties();
    }

    private void closeConnectionQuietly() {
        this.response.body().close();
        this.response = null;
        this.responseByteStream = null;
    }

    private Request makeRequest(DataSpec dataSpec2) {
        long j = dataSpec2.position;
        long j2 = dataSpec2.length;
        boolean isFlagSet = dataSpec2.isFlagSet(1);
        Request.Builder url = new Request.Builder().url(HttpUrl.parse(dataSpec2.uri.toString()));
        if (this.cacheControl != null) {
            url.cacheControl(this.cacheControl);
        }
        if (this.defaultRequestProperties != null) {
            for (Map.Entry<String, String> entry : this.defaultRequestProperties.getSnapshot().entrySet()) {
                url.header(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry2 : this.requestProperties.getSnapshot().entrySet()) {
            url.header(entry2.getKey(), entry2.getValue());
        }
        if (!(j == 0 && j2 == -1)) {
            String str = "bytes=" + j + "-";
            if (j2 != -1) {
                str = str + ((j + j2) - 1);
            }
            url.addHeader("Range", str);
        }
        url.addHeader("User-Agent", this.userAgent);
        if (!isFlagSet) {
            url.addHeader("Accept-Encoding", "identity");
        }
        if (dataSpec2.postBody != null) {
            url.post(RequestBody.create((MediaType) null, dataSpec2.postBody));
        }
        return url.build();
    }

    private int readInternal(byte[] buffer, int offset, int readLength) throws IOException {
        if (readLength == 0) {
            return 0;
        }
        if (this.bytesToRead != -1) {
            long j = this.bytesToRead - this.bytesRead;
            if (j == 0) {
                return -1;
            }
            readLength = (int) Math.min((long) readLength, j);
        }
        int read = this.responseByteStream.read(buffer, offset, readLength);
        if (read != -1) {
            this.bytesRead += (long) read;
            if (this.listener != null) {
                this.listener.onBytesTransferred(this, read);
            }
            return read;
        } else if (this.bytesToRead == -1) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped != this.bytesToSkip) {
            byte[] andSet = skipBufferReference.getAndSet(null);
            if (andSet == null) {
                andSet = new byte[4096];
            }
            while (this.bytesSkipped != this.bytesToSkip) {
                int read = this.responseByteStream.read(andSet, 0, (int) Math.min(this.bytesToSkip - this.bytesSkipped, (long) andSet.length));
                if (Thread.interrupted()) {
                    throw new InterruptedIOException();
                } else if (read == -1) {
                    throw new EOFException();
                } else {
                    this.bytesSkipped += (long) read;
                    if (this.listener != null) {
                        this.listener.onBytesTransferred(this, read);
                    }
                }
            }
            skipBufferReference.set(andSet);
        }
    }

    /* access modifiers changed from: protected */
    public final long bytesRead() {
        return this.bytesRead;
    }

    /* access modifiers changed from: protected */
    public final long bytesRemaining() {
        return this.bytesToRead == -1 ? this.bytesToRead : this.bytesToRead - this.bytesRead;
    }

    /* access modifiers changed from: protected */
    public final long bytesSkipped() {
        return this.bytesSkipped;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearRequestProperty(String name) {
        Assertions.checkNotNull(name);
        this.requestProperties.remove(name);
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        if (this.opened) {
            this.opened = false;
            if (this.listener != null) {
                this.listener.onTransferEnd(this);
            }
            closeConnectionQuietly();
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public Map<String, List<String>> getResponseHeaders() {
        if (this.response == null) {
            return null;
        }
        return this.response.headers().toMultimap();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        if (this.response == null) {
            return null;
        }
        return Uri.parse(this.response.request().url().toString());
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec2) throws HttpDataSourceException {
        this.dataSpec = dataSpec2;
        this.bytesRead = 0;
        this.bytesSkipped = 0;
        Request makeRequest = makeRequest(dataSpec2);
        try {
            this.response = this.callFactory.newCall(makeRequest).execute();
            this.responseByteStream = this.response.body().byteStream();
            int code = this.response.code();
            if (!this.response.isSuccessful()) {
                Map<String, List<String>> multimap = makeRequest.headers().toMultimap();
                closeConnectionQuietly();
                InvalidResponseCodeException invalidResponseCodeException = new InvalidResponseCodeException(code, multimap, dataSpec2);
                if (code == 416) {
                    invalidResponseCodeException.initCause(new DataSourceException(0));
                }
                throw invalidResponseCodeException;
            }
            MediaType contentType = this.response.body().contentType();
            String mediaType = contentType != null ? contentType.toString() : null;
            if (this.contentTypePredicate == null || this.contentTypePredicate.evaluate(mediaType)) {
                this.bytesToSkip = (code != 200 || dataSpec2.position == 0) ? 0 : dataSpec2.position;
                if (dataSpec2.length != -1) {
                    this.bytesToRead = dataSpec2.length;
                } else {
                    long contentLength = this.response.body().contentLength();
                    this.bytesToRead = contentLength != -1 ? contentLength - this.bytesToSkip : -1;
                }
                this.opened = true;
                if (this.listener != null) {
                    this.listener.onTransferStart(this, dataSpec2);
                }
                return this.bytesToRead;
            }
            closeConnectionQuietly();
            throw new InvalidContentTypeException(mediaType, dataSpec2);
        } catch (IOException e) {
            throw new HttpDataSourceException("Unable to connect to " + dataSpec2.uri.toString(), e, dataSpec2, 1);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource, com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] buffer, int offset, int readLength) throws HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(buffer, offset, readLength);
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.dataSpec, 2);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void setRequestProperty(String name, String value) {
        Assertions.checkNotNull(name);
        Assertions.checkNotNull(value);
        this.requestProperties.set(name, value);
    }
}
