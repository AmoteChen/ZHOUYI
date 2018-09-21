package com.zhouyi.zhouyi.object;

public interface HttpsListener {
    void success(final String response);
    void failed(Exception exception);
}
