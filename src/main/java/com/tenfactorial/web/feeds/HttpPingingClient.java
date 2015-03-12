package com.tenfactorial.web.feeds;

import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class HttpPingingClient implements PingingClient {
    private final HttpClient httpClient;
    private static final String ENDPOINT_URL = "http://feedburner-pinger.herokuapp.com/";

    public HttpPingingClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpPingingClient() {
        this(HttpClients.createMinimal());
    }

    @Override
    public PingResponse ping(PingRequest request) throws IOException {
        String url = request.getUrl();

        HttpPost post = new HttpPost(ENDPOINT_URL);

        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("url", url));

        post.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = this.httpClient.execute(post);

        return this.getResponse(response.getEntity().getContent());
    }

    private PingResponse getResponse(InputStream input) throws IOException {
        try (JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(input)))) {
            return this.readResponse(reader);
        }
    }

    private PingResponse readResponse(JsonReader reader) throws IOException {
        PingStatus status = PingStatus.FAILED;
        String message = null;

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("status")) {
                status = PingStatus.valueOf(reader.nextString());
            } else if (name.equals("message")) {
                message = reader.nextString();
            }
        }

        reader.endObject();

        return new PingResponse(status, message);
    }
}
