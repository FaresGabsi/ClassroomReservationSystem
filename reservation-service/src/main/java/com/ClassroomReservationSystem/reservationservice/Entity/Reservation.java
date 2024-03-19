package com.ClassroomReservationSystem.reservationservice.Entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Reservation {
    @Id
    @GeneratedValue
    private Long resId;
    private String classId;
    // List of séances
    @ElementCollection
    private List<Seance> seances;
    private Date reservationDate;
    //private Long user_id;
}
