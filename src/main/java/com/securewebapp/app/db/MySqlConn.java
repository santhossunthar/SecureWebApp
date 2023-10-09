package com.securewebapp.app.db;

import java.sql.*;

public class MySqlConn {
    private static final String URL = "jdbc:mysql://51.132.137.223:3306/isec_assessment2";
    private static final String USER = "isec";
    private static final String PASSWORD = "EUHHaYAmtzbv";

    public Connection connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
