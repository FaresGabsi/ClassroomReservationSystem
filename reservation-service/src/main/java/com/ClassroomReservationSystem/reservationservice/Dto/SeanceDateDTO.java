package com.ClassroomReservationSystem.reservationservice.Dto;

import com.ClassroomReservationSystem.reservationservice.Entity.Seance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeanceDateDTO {
    private List<Seance> seances;
    private Date reservationDate;
}
