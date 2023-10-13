package com.securewebapp.app.connection;

import com.securewebapp.app.auth.AuthConfig;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySqlConn {
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties = new Properties();

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

    private MySqlConn(){}

    public static Connection connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(
                    properties.getProperty("mysql.conn.url"),
                    properties.getProperty("mysql.conn.user"),
                    properties.getProperty("mysql.conn.password"));
            return connection;
        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
