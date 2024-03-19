package com.ClassroomReservationSystem.reservationservice.Services;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.ClassroomReservationSystem.reservationservice.Entity.Seance;
import com.ClassroomReservationSystem.reservationservice.Feign.ClassroomClient;
import com.ClassroomReservationSystem.reservationservice.Repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ClassroomClient classroomClient;

    @Override
    public Reservation addReservation(Reservation reservation) {
        // Check if reservation is valid
        if(isValid(reservation)){
            return reservationRepository.save(reservation);
        }
        else
            throw new RuntimeException("Invalid reservation");
    }

    // Check if reservation is valid
    private boolean isValid(Reservation reservation) {
        /*//check if class exists in classroom service and if the seances are valid and if there are no other reservations for the same class at the same time
        if(classroomClient.getClassroomById(reservation.getClassId()) != null){
            //check if there are no other reservations for the same class at the same
            List<Reservation>reservations=getAllReservations();
            for(Reservation res:reservations){
                if (res.getReservationDate().equals(reservation.getReservationDate())) {
                    if(res.getSeances().contains(reservation.getSeances())){
                        return false;
                    }
                }
                }
            }
        return true;*/
        // Check if class exists in classroom service
        if (classroomClient.getClassroomById(reservation.getClassId()) == null) {
            return false;
        }
        List<Reservation> reservations = getAllReservations();
        //noneMatch returns true if no elements of the stream match the given predicate
        //anyMatch returns true if any elements of the stream match the given predicate
        return reservations.stream().noneMatch(res->
                res.getReservationDate().equals(reservation.getReservationDate())&&
                res.getSeances().stream().anyMatch(seance -> reservation.getSeances().contains(seance)));
    }

    @Override
    public void deleteReservation(Long resId) {
        if(getReservation(resId).isPresent())
            reservationRepository.deleteById(resId);
        else
            throw new RuntimeException("Reservation not found with id: " + resId);
    }

    @Override
    public Reservation updateReservation(Long resId, Reservation updatedReservation) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);
        if(reservation.isPresent()){
            Reservation res = reservation.get();
            res.setClassId(updatedReservation.getClassId());
            res.setReservationDate(updatedReservation.getReservationDate());
            res.setSeances(updatedReservation.getSeances());
            return reservationRepository.save(res);
        }
        else
            throw new RuntimeException("Reservation not found with id: " + resId);
    }


    @Override
    public Optional<Reservation> getReservation(Long resId) {
        Optional<Reservation> reservation = reservationRepository.findById(resId);
        if(reservation.isPresent()){
            return reservation;
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
