package com.tenfactorial.web.feeds;

public class PingResponse {
    private final PingStatus status;
    private final String message;

    public PingResponse(PingStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PingStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
