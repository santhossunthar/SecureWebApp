package com.securewebapp.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    private Algorithm algorithm = Algorithm.HMAC256("your_secret_key");
    private JWTVerifier verifier = JWT.require(algorithm).build();
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String code = request.getParameter("code");

        // Getting ID Token
        String idToken = exchangeCodeForIdToken(code);
        System.out.println(idToken);
    }

    private String exchangeCodeForIdToken(String code) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String idToken = null;

        try {
            // POST request to Okta's Token endpoint
            HttpPost httpPost = new HttpPost("https://dev-q4sknn3f2n0c67zb.us.auth0.com/oauth2/v1/token");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // Request body with grant_type, code, and redirect_uri
            String requestBody = "grant_type=authorization_code" +
                    "&code=" + code +
                    "&redirect_uri=https://localhost:8080/callback";

            httpPost.setEntity(new StringEntity(requestBody));

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println(responseBody);
            /*
            if (responseBody != null) {
                JSObject json = new JSObject(jsonResponse);

                if (json.has("id_token")) {
                    idToken = json.getString("id_token");
                }
            }
             */
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return idToken;
    }
}