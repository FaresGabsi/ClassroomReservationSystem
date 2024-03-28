package com.ClassroomReservationSystem.reservationservice.Feign;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Classroom {
    @Id
    private String classroomId;
    private Integer capacity;
    private List<Equipment> equipments;
}
