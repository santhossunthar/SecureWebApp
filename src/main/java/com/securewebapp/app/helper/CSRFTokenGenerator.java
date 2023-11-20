package com.securewebapp.app.helper;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.servlet.CallbackServlet;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSRFTokenGenerator {
    private static final Logger logger = Logger.getLogger(CallbackServlet.class.getName());

    public String generate() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] data = new byte[16];
            secureRandom.nextBytes(data);
            return Base64.getEncoder().encodeToString(data);
        } catch (NoSuchAlgorithmException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return null;
    }
}
