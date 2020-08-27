package com.zhenmei.mvpmamba.net.download;


import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class JsResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private JsDownloadListener downloadListener;
    // BufferedSource 是okio库中的输入流，这里就当作inputStream来使用。
    private BufferedSource bufferedSource;

    public JsResponseBody(ResponseBody responseBody, JsDownloadListener downloadListener) {
        this.responseBody = responseBody;
        this.downloadListener = downloadListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
// read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                Log.e("download", "read: " + (int) (totalBytesRead * 100 / responseBody.contentLength()));
//                if (null != downloadListener) {
//                    if (bytesRead != -1) {
//                        downloadListener.onProgress((int) (totalBytesRead * 100 / responseBody.contentLength()));
//                    }
//
//                }


                Observable.just(downloadListener)
                        .filter(new Predicate<JsDownloadListener>() {

                            @Override
                            public boolean test(JsDownloadListener jsDownloadListener) throws Exception {
                                return jsDownloadListener != null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<JsDownloadListener>() {
                            @Override
                            public void accept(JsDownloadListener jsDownloadListener) throws Exception {
                                jsDownloadListener.onProgress((int) (totalBytesRead * 100 / responseBody.contentLength()), responseBody.contentLength());


                            }
                        });


                return bytesRead;
            }
        };

    }
}
