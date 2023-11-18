package com.securewebapp.app.repository.impl;

import java.util.HashMap;
import java.util.List;

public interface IReservationRepository {
    List<HashMap<String, Object>> getReservationsDetails(String userId);
    List<HashMap<String, Object>> getReservationsDetails(String userId, String bookingId);
    boolean addReservationDetails(HashMap<String, String> reservationData);
    boolean deleteReservationDetailsById(String bookingId, String userId);
}
