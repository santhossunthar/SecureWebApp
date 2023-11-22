package com.securewebapp.app.auth;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class AuthConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AuthConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Unable to find " + CONFIG_FILE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDomain() {
        return properties.getProperty("okta.client.domain");
    }
    public String getClientId() {
        return properties.getProperty("okta.client.id");
    }
    public String getClientSecret() {
        return properties.getProperty("okta.client.secret");
    }

    public String getScope() {
        return properties.getProperty("okta.client.scope");
    }
}
