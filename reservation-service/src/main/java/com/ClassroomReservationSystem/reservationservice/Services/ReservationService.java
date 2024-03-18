package com.ClassroomReservationSystem.reservationservice.Services;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    void deleteReservation(Long resId);
    Reservation updateReservation(Long resId, Reservation updatedReservation);
    Optional<Reservation> getReservation(Long resId);
    List<Reservation> getAllReservations();

}
