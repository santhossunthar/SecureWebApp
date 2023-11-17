package com.securewebapp.app.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.io.IOException;

public class AuthUser {
    private String accessToken;
    private String domain;

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
        } catch (UnirestException ex){
            throw new UnirestException(ex);
        }

        return null;
    }
}
