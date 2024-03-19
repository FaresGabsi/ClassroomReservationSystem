package com.ClassroomReservationSystem.reservationservice.Feign;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Classroom {
    @Id
    private String id_classroom;
    private Integer capacity;
}
