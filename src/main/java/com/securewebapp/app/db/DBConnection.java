package com.securewebapp.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBConnection {
    private MySqlConn mySqlConn = new MySqlConn();

    public List<HashMap<String, Object>> getReservationsDetails(){
        try {
            Connection conn =  mySqlConn.connect();
            String sql = "SELECT * FROM vehicle_service";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<HashMap<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                HashMap data = new HashMap();
                data.put("bookingId", resultSet.getInt("booking_id"));
                data.put("date", resultSet.getString("date"));
                data.put("time", resultSet.getString("time"));
                data.put("location", resultSet.getString("location"));
                data.put("vehicleNo", resultSet.getString("vehicle_no"));
                data.put("mileage", resultSet.getString("mileage"));
                dataList.add(data);
            }

            return dataList;
        }catch (SQLException ex){
            System.out.println(ex);
        }

        return null;
    }

    public List<HashMap> getReservationDetailsByUserEmail(String userEmail){
        try {
            Connection conn =  mySqlConn.connect();
            String sql = "SELECT * FROM vehicle_service WHERE username=" + userEmail;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<HashMap> dataList = new ArrayList<>();
            while (resultSet.next()) {
                HashMap data = new HashMap();
                data.put("bookingId", resultSet.getInt("booking_id"));
                data.put("username", resultSet.getString("username"));
                dataList.add(data);
            }
            conn.close();
            return dataList;
        }catch (SQLException ex){
            System.out.println(ex);
        }

        return null;
    }

    public boolean addReservationDetails(String reservationData){
        try {
            Connection conn =  mySqlConn.connect();
            String sql = "INSERT INTO vehicle_service(" +
                    "booking_id, date, time, location, vehicle_no, mileage, message, username) VALUES(? ? ? ? ? ? ? ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, reservationData);
            int rowsInserted = preparedStatement.executeUpdate();

            boolean response;
            if (rowsInserted > 0) {
                response = true;
            } else {
                response = false;
            }

            preparedStatement.close();
            conn.close();

            return response;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return false;
    }

    public boolean deleteReservationDetailsById(int bookingId){
        try {
            Connection conn =  mySqlConn.connect();
            String sql = "DELETE FROM vehicle_service WHERE username=" + bookingId;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return false;
    }
}
