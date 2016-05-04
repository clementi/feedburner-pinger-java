package com.tenfactorial.web.feeds;

import com.tenfactorial.web.feeds.api.FeedBurnerPinger;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class HttpPingingClient implements PingingClient {
    private static final String ENDPOINT_URL = "http://feedburner-pinger.herokuapp.com";
    private final FeedBurnerPinger pinger;

    public HttpPingingClient(FeedBurnerPinger pinger) {
        this.pinger = pinger;
    }

    public HttpPingingClient() {
        this(createPingerService());
    }

    private static FeedBurnerPinger createPingerService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FeedBurnerPinger.class);
    }

    public PingResponse ping(PingRequest request) throws IOException {
        String url = request.getUrl();
        Call<PingResponse> responseCall = pinger.ping(url);

        return responseCall.execute().body();
    }
}
