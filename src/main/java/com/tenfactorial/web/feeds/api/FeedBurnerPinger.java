package com.tenfactorial.web.feeds.api;

import com.tenfactorial.web.feeds.PingResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedBurnerPinger {
    @POST("/")
    @FormUrlEncoded
    Call<PingResponse> ping(@Field("url") String url);
}
