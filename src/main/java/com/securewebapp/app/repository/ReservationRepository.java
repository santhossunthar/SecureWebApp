package com.securewebapp.app.repository;

import com.securewebapp.app.connection.MySqlConn;
import com.securewebapp.app.repository.impl.IReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationRepository implements IReservationRepository {
    private static final Logger logger = Logger.getLogger(ReservationRepository.class.getName());

    public List<HashMap<String, Object>> getReservationsDetails(String userId){
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null) {
                String sql = "SELECT booking_id, date, time, location FROM vehicle_service WHERE username=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<HashMap<String, Object>> reservationDataList = new ArrayList<>();
                while (resultSet.next()) {
                    HashMap<String, Object> reservationData = new HashMap<>();
                    reservationData.put("bookingId", resultSet.getInt("booking_id"));
                    reservationData.put("date", resultSet.getString("date"));
                    reservationData.put("time", resultSet.getString("time"));
                    reservationData.put("location", resultSet.getString("location"));
                    reservationDataList.add(reservationData);
                }

                return reservationDataList;
            }
        }catch (SQLException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public HashMap<String, Object> getReservationDetails(String userId, String bookingId) {
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null) {
                String sql = "SELECT booking_id, date, time, location, vehicle_no, mileage, message " +
                        "FROM vehicle_service WHERE username=? AND booking_id=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, bookingId);
                ResultSet resultSet = preparedStatement.executeQuery();

                HashMap<String, Object> reservationData = new HashMap<>();
                while (resultSet.next()) {
                    reservationData.put("bookingId", resultSet.getInt("booking_id"));
                    reservationData.put("date", resultSet.getString("date"));
                    reservationData.put("time", resultSet.getString("time"));
                    reservationData.put("location", resultSet.getString("location"));
                    reservationData.put("vehicleNo", resultSet.getString("vehicle_no"));
                    reservationData.put("mileage", resultSet.getString("mileage"));
                    reservationData.put("message", resultSet.getString("message"));
                }

                return reservationData;
            }
        }catch (SQLException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
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
                response = rowsInserted > 0;

                preparedStatement.close();
                conn.close();

                return response;
            }
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return false;
    }

    public boolean deleteReservationDetailsById(String bookingId, String userId){
        try {
            Connection conn =  MySqlConn.connect();

            if(conn != null){
                String sql = "DELETE FROM vehicle_service WHERE username=? AND booking_id=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, bookingId);
                preparedStatement.executeUpdate();

                return true;
            }
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
        }

        return false;
    }
}
