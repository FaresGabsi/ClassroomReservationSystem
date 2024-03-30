package com.ClassroomReservationSystem.reservationservice.Services;

import com.ClassroomReservationSystem.reservationservice.Entity.Reservation;
import com.ClassroomReservationSystem.reservationservice.Entity.Seance;
import com.ClassroomReservationSystem.reservationservice.Feign.Classroom;
import com.ClassroomReservationSystem.reservationservice.Feign.ClassroomClient;
import com.ClassroomReservationSystem.reservationservice.Feign.Equipment;
import com.ClassroomReservationSystem.reservationservice.Repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ClassroomClient classroomClient;

    @Override
    public Reservation addReservation(Reservation reservation) {
        // Check if reservation is valid
        if(!isValidClass(reservation.getClassId()))
            throw new RuntimeException("Class not found with id: " + reservation.getClassId());
        if(!isValidDate(reservation))
            throw new RuntimeException("Invalid reservation");
        return reservationRepository.save(reservation);
    }

    // Check if reservation is valid
    private boolean isValidDate(Reservation reservation) {
        List<Reservation> reservations = getAllReservations();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return reservations.stream().noneMatch(res->
                dateFormat.format(res.getReservationDate()).equals(dateFormat.format(reservation.getReservationDate()))&&
                        res.getSeances().stream().anyMatch(seance -> reservation.getSeances().contains(seance))&&res.getClassId().equals(reservation.getClassId()));
    }
    private boolean isValidClass(String classId) {
        return classroomClient.getClassroomById(classId) != null;
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
            if(!isValidClass(res.getClassId()))
                throw new RuntimeException("Class not found with id: " + res.getClassId());
            if(isValidDate(res))
                return reservationRepository.save(res);
            else
                throw new RuntimeException("Invalid reservation");
        }
        else
            throw new RuntimeException("Reservation not found with id: " + resId);
    }


    @Override
    public Optional<Reservation> getReservation(Long resId) {
        return reservationRepository.findById(resId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Classroom> getRecommandation(Long minCapacity, List<Equipment> requiredEquipment, List<Seance> seances, String reservationDate) {
        List<Classroom> allClassrooms = classroomClient.getAllClassrooms();
        if(minCapacity!=null){
            System.out.println("testing capacity");allClassrooms = allClassrooms.stream()
                .filter(classroom -> classroom.getCapacity() >= minCapacity).collect(Collectors.toList());}
        if(requiredEquipment !=null ){System.out.println("testing equipments");
            allClassrooms=allClassrooms.stream().filter(classroom -> classroom.getEquipments().containsAll(requiredEquipment))
                .collect(Collectors.toList());}
        System.out.println(reservationDate);
        System.out.println(reservationDate!=null);
        if (reservationDate != null) { System.out.println("testing date");
            allClassrooms = allClassrooms.stream()
                    .filter(classroom -> isAvailableOnDate(classroom, reservationDate))
                    .collect(Collectors.toList());
        }
        if(seances!=null){   allClassrooms = allClassrooms.stream()
                .filter(classroom -> isAvailableOnSession(classroom, seances,reservationDate))
                .collect(Collectors.toList());

        }

     return allClassrooms;
    }
    private boolean isAvailableOnDate(Classroom classroom, String reservationDateStr) {
        // Convert the reservationDate string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date reservationDate = null;
        try {
            reservationDate = dateFormat.parse(reservationDateStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception as needed
            return false; // Return false if parsing fails
        }
        System.out.println(reservationDate);

        List<Reservation> reservationsForClassroom = reservationRepository.findByClassIdAndReservationDate(classroom.getClassroomId(), reservationDate);

        if (reservationsForClassroom.isEmpty()) {
            System.out.println("No reservation for  "+ classroom.getClassroomId());
            return true;
        }
        Set<Seance> reservedSessions = new HashSet<>();

        // Collect all reserved sessions
        for (Reservation reservation : reservationsForClassroom) {
            reservedSessions.addAll(reservation.getSeances());
            System.out.println(reservation.getReservationDate());
        }

        System.out.println("Reserved sessions for classroom " + classroom.getClassroomId() + " on " + reservationDate + ": " + reservedSessions);
        System.out.println(reservedSessions.size() < Seance.values().length);

        // Check if all sessions are reserved
        return reservedSessions.size() < Seance.values().length;
}
private boolean isAvailableOnSession(Classroom classroom,List<Seance> seances,String reservationDateStr){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date reservationDate = null;
    try {
        reservationDate = dateFormat.parse(reservationDateStr);
    } catch (ParseException e) {
        e.printStackTrace(); // Handle parsing exception as needed
        return false; // Return false if parsing fails
    }
    System.out.println(reservationDate);

    List<Reservation> reservationsForClassroom = reservationRepository.findByClassIdAndReservationDate(classroom.getClassroomId(), reservationDate);

    if (reservationsForClassroom.isEmpty()) {
        System.out.println("sessions available classroom "+ classroom.getClassroomId());
        return true;
    }
    Set<Seance> reservedSessions = new HashSet<>();

    // Collect all reserved sessions
    for (Reservation reservation : reservationsForClassroom) {
        reservedSessions.addAll(reservation.getSeances());
    } for (Seance seance : seances) {
        if (reservedSessions.contains(seance)) {
            System.out.println("Seance " + seance + " already reserved for classroom " + classroom.getClassroomId() + " on " + reservationDate);
            return false;
        }
    }


    System.out.println("All provided seances are available for classroom " + classroom.getClassroomId() + " on " + reservationDate);
    return true;

}}
