package com.securewebapp.app.auth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public JSONObject getInfo() throws IOException {
        try {
            String userInfoUrl = "https://" + domain + "/userinfo";

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(userInfoUrl))
                    .header("Authorization", "Bearer " + accessToken)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());

            if (response.statusCode() == 200) {
                return jsonObject;
            } else {
                logger.log(Level.WARNING, "An error occurred while retrieving user profile details");
            }
        } catch (InterruptedException | JSONException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return null;
    }
}
