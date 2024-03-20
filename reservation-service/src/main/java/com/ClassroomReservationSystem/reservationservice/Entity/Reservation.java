package com.ClassroomReservationSystem.reservationservice.Entity;

import jakarta.persistence.*;
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
    @Temporal(TemporalType.DATE)
    private Date reservationDate;
}
