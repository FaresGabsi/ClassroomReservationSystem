package com.ClassroomReservationSystem.reservationservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLASSROOM-SERVICE", url = "http://localhost:8081")
public interface ClassroomClient {
    @GetMapping("classroom/{id}")
    public Classroom getClassroomById(@PathVariable("id") String id);
}
