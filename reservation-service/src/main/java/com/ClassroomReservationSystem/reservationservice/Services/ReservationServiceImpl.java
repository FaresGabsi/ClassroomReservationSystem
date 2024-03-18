package com.ClassroomReservationSystem.reservationservice.Services;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.ClassroomReservationSystem.reservationservice.Repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;


    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
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
