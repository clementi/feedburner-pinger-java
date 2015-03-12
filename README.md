# feedburner-pinger-java

## Description

This is a Java client library for access to the FeedBurner-Pinger service at http://feedburner-pinger.herokuapp.com.

## Usage

Use the client like this:

```java
PingingClient client = new HttpPingingClient();

PingRequest request = new PingRequest("http://url-to-your-feedburner-feed");
PingResponse response = client.ping(request);
```

The `PingResponse` has two getters: `getStatus` and `getMessage`. `getStatus` returns an enum with values `SUCCEEDED`, `THROTTLED`, or `FAILED`. `getMessage` returns a string which gives a human-readable version of the status. More information on this can be found at http://feedburner-pinger.herokuapp.com.
