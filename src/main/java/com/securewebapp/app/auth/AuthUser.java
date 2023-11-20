package com.securewebapp.app.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthUser {
    private final String accessToken;
    private final String domain;
    private static final Logger logger = Logger.getLogger(AuthUser.class.getName());

    public AuthUser(String domain, String accessToken){
        this.domain = domain;
        this.accessToken = accessToken;
    }
    public JsonNode getInfo() throws UnirestException, IOException {
        try {
            String userInfoUrl = "https://" + domain + "/userinfo";

            HttpResponse<JsonNode> response = Unirest.get(userInfoUrl)
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();

            if (response.getStatus() == 200) {
                return response.getBody();
            } else {
                System.out.println("Error");
            }

            Unirest.shutdown();
        } catch (UnirestException | IOException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return null;
    }
}
