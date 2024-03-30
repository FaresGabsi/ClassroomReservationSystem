package com.ClassroomReservationSystem.reservationservice.Controller;

import com.ClassroomReservationSystem.reservationservice.Dto.SeanceDateDTO;
import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.ClassroomReservationSystem.reservationservice.Entity.Seance;
import com.ClassroomReservationSystem.reservationservice.Feign.Classroom;
import com.ClassroomReservationSystem.reservationservice.Feign.Equipment;
import com.ClassroomReservationSystem.reservationservice.Services.ReservationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PostMapping(value = "/recommandation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Classroom>> getRecommandation(
            @RequestParam(required = false) Long minCapacity,
            @RequestParam(required = false) List<Equipment> requiredEquipment,
            @RequestBody(required = false) SeanceDateDTO seanceDateDTO,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationDate) {

        List<Seance> seances = null;
        String formattedDate = null;

        if (seanceDateDTO != null) {
            seances = seanceDateDTO.getSeances();
            Date dtoReservationDate = seanceDateDTO.getReservationDate();
            formattedDate = dtoReservationDate != null ? formatDate(dtoReservationDate) : null;
        } else if (reservationDate != null) {
            formattedDate = formatDate(reservationDate);
        }

        List<Classroom> recommandation = reservationService.getRecommandation(minCapacity, requiredEquipment, seances, formattedDate);
        return new ResponseEntity<>(recommandation, HttpStatus.OK);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation){
        try {
            Reservation res = reservationService.addReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reservation added with id: " + res.getResId());
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add Reservation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{resId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("resId") Long resId){
        try {
            reservationService.deleteReservation(resId);
            return ResponseEntity.ok("Reservation deleted with id: " + resId);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to delete Reservation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{resId}")
    public ResponseEntity<String> updateReservation(@PathVariable("resId") Long resId, @RequestBody Reservation updatedReservation){
        try {
            Reservation res = reservationService.updateReservation(resId, updatedReservation);
            return ResponseEntity.ok("Reservation updated with id: " + res.getResId());
        }catch (Exception e){
            return new ResponseEntity<>("Failed to update Reservation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
