package com.tenfactorial.web.feeds;

public class Program {
    public static void main(String[] args) {
        PingingClient client = new HttpPingingClient();

        try {
            PingResponse response = client.ping(new PingRequest("http://feeds.feedburner.com/TheTomWoodsShow"));
            System.out.println(response.getStatus().toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
