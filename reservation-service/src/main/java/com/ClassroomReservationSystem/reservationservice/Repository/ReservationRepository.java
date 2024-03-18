package com.ClassroomReservationSystem.reservationservice.Repository;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
