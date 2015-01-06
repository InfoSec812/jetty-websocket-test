/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket;

import com.beust.jcommander.JCommander;
import java.net.URI;
import java.nio.file.Files;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 *
 * @author dphillips
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        Configuration config = new Configuration();
        JCommander parser = new JCommander();
        parser.setProgramName("websocket-test");
        parser.addObject(config);
        try {
            parser.parse(args);
        } catch (Exception e) {
            LOG.error("Error parsing configuration.", e);
            return;
        }

        WebSocketClient client = new WebSocketClient(new SslContextFactory(config.unsafeSsl()));
        try {
            Handler hdlr = new Handler();
            client.start();
            URI sinkTarget = new URI(config.serverUrl()+"?for=sungardas");
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            if (config.headers()!=null) {
                for (String line: Files.readAllLines(config.headers().toPath())) {
                    LOG.info("Header Line| "+line);
                    String[] header = line.split(":", 2);
                    request.setHeader(header[0], header[1]);
                }
            }
            client.connect(hdlr, sinkTarget, request);
        } catch (Exception e) {
            LOG.error("Error performing WebSocket connection", e);
            return;
        }
        
        try {
            Thread.sleep(config.timeout());
        } catch (InterruptedException ie) {
            LOG.error("Thread sleep error", ie) ;
        }
    }
}
