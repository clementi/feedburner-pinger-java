package com.metronomix.web.feeds;

import java.io.IOException;

public interface PingingClient {
    public PingResponse ping(PingRequest request) throws IOException;
}
