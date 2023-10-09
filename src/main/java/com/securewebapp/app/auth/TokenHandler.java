package com.securewebapp.app.auth;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TokenHandler {
    public String[] exchangeCodeForIdToken(String code){
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .build())
                .build();
        String[] tokens = new String[2];

        try {
            // POST request to Okta's Token endpoint
            HttpPost httpPost = new HttpPost("https://dev-q4sknn3f2n0c67zb.us.auth0.com/oauth/token");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            String auth = "0v0Qb81hBN2hKC4EaOzr11WIq3hUGNB7" + ":" + "BcmIMR3Un8s1Q_dwwIU3S-n4RZx2X4aNkAJxCP_8eDp0wgYKjlhr3m-dynHbT2-C";
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);
            httpPost.setHeader("Authorization", authHeader);

            // Set the request parameters
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/callback"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: ");
                }
            };

            String responseBody = httpClient.execute(httpPost, responseHandler);

            if (responseBody != null) {
                JSONObject json = new JSONObject(responseBody);
                if (json.has("id_token")) {
                    tokens[0] = json.getString("id_token");
                }

                if(json.has("access_token")){
                    tokens[1] = json.getString("access_token");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return tokens;
    }
}
