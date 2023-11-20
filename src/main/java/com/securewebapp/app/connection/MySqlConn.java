package com.securewebapp.app.connection;

import com.securewebapp.app.auth.AuthConfig;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConn {
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(MySqlConn.class.getName());

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

    private MySqlConn() {}

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("mysql.conn.url"),
                    properties.getProperty("mysql.conn.user"),
                    properties.getProperty("mysql.conn.password"));
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return null;
    }
}
