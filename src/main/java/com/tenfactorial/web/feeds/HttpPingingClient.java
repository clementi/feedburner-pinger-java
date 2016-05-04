package com.tenfactorial.web.feeds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;

public class HttpPingingClient implements PingingClient {
    private final OkHttpClient client;
    private static final String URL_PARAMETER_NAME = "url";
    private static final String ENDPOINT_URL = "http://feedburner-pinger.herokuapp.com";

    public HttpPingingClient(OkHttpClient client) {
        this.client = client;
    }

    public HttpPingingClient() {
        this(new OkHttpClient());
    }

    public PingResponse ping(PingRequest request) throws IOException {
        String url = request.getUrl();

        RequestBody formBody = new FormBody.Builder()
                .add(URL_PARAMETER_NAME, url)
                .build();

        Request req = new Request.Builder()
                .url(ENDPOINT_URL)
                .post(formBody)
                .build();

        Response resp = client.newCall(req).execute();
        if (!resp.isSuccessful()) {
            throw new IOException(resp.toString());
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(resp.body().charStream(), PingResponse.class);
    }
}
