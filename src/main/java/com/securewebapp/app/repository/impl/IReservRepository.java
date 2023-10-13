package com.securewebapp.app.repository.impl;

import java.util.HashMap;
import java.util.List;

public interface IReservRepository {
    List<HashMap<String, Object>> getReservationsDetails(String userId);
    boolean addReservationDetails(HashMap<String, String> reservationData);
    boolean deleteReservationDetailsById(String bookingId);
}
