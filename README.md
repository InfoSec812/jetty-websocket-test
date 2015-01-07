Jetty WebSocket Client
======================

A simple Jetty 9.2.x based WebSocket client which just logs whatever the 
connected server sends to it.

Prerequisites
-------------

1. Maven 3.x
2. Java >= 1.7

Running
-------

    mvn java:exec -Dexec.args="-s ws[s]://host:port/path/to/websocket -t 30000 --header-file /path/to/headers"

The header-file is a text file containing HTTP headers to be sent with the 
WebSocket UPGRADE request in the format of:

    HeaderName: Value:Continuation

The headers are split on the FIRST colon, but subsequent colons are retained
in the header value.

Command-Line Options
--------------------
    Usage: websocket-test [options]
      Options:
      --header-file
           A file container headers to be appended to all requests
      -s, --server-url 
           The Websocket server to connect to. (ws[s]://host:port/path/to/websocket)
           REQUIRED
      -t, --timeout
           The amount of time to run before exiting
           Default: 5000
      --unsafe-ssl
           Disable SSL certificate validation
           Default: false
