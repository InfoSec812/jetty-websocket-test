/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.websocket;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.io.File;
import lombok.Data;

/**
 *
 * @author dphillips
 */
@Parameters(separators = "= ")
@Data
public class Configuration {
    
    @Parameter(required = true, names = {"-s", "--server-url"}, description = "The Websocket server to connect to. (ws[s]://host:port/path/to/websocket)")
    private String serverUrl;

    @Parameter(names={"-t", "--timeout"}, description = "The amount of time to run before exiting")
    private Integer timeout = 5000;

    @Parameter(names={"--unsafe-ssl"}, description = "Disable SSL certificate validation")
    private Boolean unsafeSsl = false;

    @Parameter(names={"--header-file"}, description = "A file container headers to be appended to all requests")
    private File headers = null;
}
