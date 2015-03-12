package com.tenfactorial.web.feeds;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        PingingClient client = new HttpPingingClient();

        PingResponse response = client.ping(new PingRequest("http://feeds.feedburner.com/JeffreyPratt"));
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());
    }
}
