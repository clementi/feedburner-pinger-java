package com.tenfactorial.web.feeds;

public class PingRequest {
    private final String url;

    public PingRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
