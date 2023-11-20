package com.securewebapp.app.auth;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(AuthConfig.class.getName());

    static {
        try (InputStream input = AuthConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
            } else {
                logger.log(Level.CONFIG, "An error occurred while reading configuration file");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
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
