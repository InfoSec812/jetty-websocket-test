/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 *
 * @author dphillips
 */
@WebSocket
@Slf4j
public class Handler {

    private Session session;

    @OnWebSocketConnect
    public void onConnect(Session session) {
        session.setIdleTimeout(-1);
        LOG.info("Connected to websocket");
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        LOG.info("Message: "+message);
    }

    @OnWebSocketError
    public void onError(Throwable error) {
        LOG.error("Error: ", error);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        LOG.warn("WebSocket Closed: "+statusCode+" - "+reason);
    }
}
