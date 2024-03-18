package com.classroomregistration.classroomservice.controller;

import com.classroomregistration.classroomservice.entity.Availability;
import com.classroomregistration.classroomservice.entity.Classroom;
import com.classroomregistration.classroomservice.service.AvailabilityService;
import com.classroomregistration.classroomservice.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private AvailabilityService availabilityService;
    @GetMapping("/classrooms")
    public List<Classroom> getAllClassrooms() {
        return classroomService.fetchAllClassrooms();
    }
    @GetMapping("classroom/{id}")
    public Classroom getClassroomById(@PathVariable("id") String id){
        return classroomService.getClassroomById(id);
    }
    @GetMapping("/availability/{id}")
    public Availability getClassroomAvailability(@PathVariable("id") String id_classroom){

    return availabilityService.getAvailabilityByClassroom(id_classroom);
    }}
