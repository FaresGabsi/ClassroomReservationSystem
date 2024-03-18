package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Availability;
import com.classroomregistration.classroomservice.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailabilityService implements AvailabilityServiceInter{
    @Autowired
    private AvailabilityRepository availabilityRepository;


    @Override
    public Availability getAvailabilityByClassroom(String id_Classroom) {

        return availabilityRepository.findAvailabilityByIdClassroom(id_Classroom);
    }
}
