package com.securewebapp.app.api;

public class ApplicationURL {
    private static final String scheme = "http";
    private static final String domainName = "localhost";
    private static final int port = 8080;

    private ApplicationURL(){}

    public static String getURL(){
        return scheme +"://"+ domainName +":"+ port;
    }
}
