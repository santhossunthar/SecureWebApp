package com.securewebapp.app.helper;

import org.owasp.encoder.Encode;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidator {
    private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    private static final String NUMERIC_PATTERN = "^[0-9]*$";

    private InputValidator(){}

    public static boolean isAlphanumeric(String input) {
        Pattern pattern = Pattern.compile(ALPHANUMERIC_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isNumeric(String input){
        Pattern pattern = Pattern.compile(NUMERIC_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isValidDate(String input){
        Pattern pattern = Pattern.compile("^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static String sanitizeHtml(String input) {
        input = Encode.forHtml(input);
        return input;
    }
}
