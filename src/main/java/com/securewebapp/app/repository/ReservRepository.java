package com.securewebapp.app.repository;

import com.securewebapp.app.connection.MySqlConn;
import com.securewebapp.app.repository.impl.IReservRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservRepository implements IReservRepository {

    public List<HashMap<String, Object>> getReservationsDetails(String userId){
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null) {
                String sql = "SELECT * FROM vehicle_service WHERE username=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userId);
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
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return null;
    }

    public boolean addReservationDetails(HashMap<String, String> reservationData){
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null){
                String sql = "INSERT INTO vehicle_service(" +
                        "date, time, location, vehicle_no, mileage, message, username) VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, reservationData.get("reservationDate"));
                preparedStatement.setString(2, reservationData.get("reservationTime"));
                preparedStatement.setString(3, reservationData.get("reservationLocation"));
                preparedStatement.setString(4, reservationData.get("reservationVehicleNo"));
                preparedStatement.setString(5, reservationData.get("reservationMileage"));
                preparedStatement.setString(6, reservationData.get("reservationMessage"));
                preparedStatement.setString(7, reservationData.get("userName"));

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
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return false;
    }

    public boolean deleteReservationDetailsById(String bookingId){
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null){
                String sql = "DELETE FROM vehicle_service WHERE username=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, bookingId);
                preparedStatement.executeQuery();
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
}
