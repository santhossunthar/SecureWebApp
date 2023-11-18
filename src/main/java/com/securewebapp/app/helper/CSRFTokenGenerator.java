package com.securewebapp.app.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CSRFTokenGenerator {
    public String generate() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] data = new byte[16];
            secureRandom.nextBytes(data);
            return Base64.getEncoder().encodeToString(data);
        } catch (NoSuchAlgorithmException e){

        }
        return null;
    }
}
