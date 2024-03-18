package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Availability;

import java.util.Optional;

public interface AvailabilityServiceInter {
   Availability getAvailabilityByClassroom(String id_classroom);

}
