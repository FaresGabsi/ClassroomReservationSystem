package com.ClassroomReservationSystem.reservationservice.Controller;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.ClassroomReservationSystem.reservationservice.Services.ReservationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations= reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation){
        try {
            Reservation res = reservationService.addReservation(reservation);
            return ResponseEntity.ok("Reservation added with id: " + res.getResId());
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add Reservation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
