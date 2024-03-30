package com.ClassroomReservationSystem.reservationservice.Repository;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Override
    List<Reservation> findAll();

    List<Reservation> findByClassIdAndReservationDate(String classId, Date reservationDate);
    List<Reservation> findByClassId(String classId);
}
