package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Classroom;

import java.util.List;

public interface ClassroomServiceInter {
    Classroom addClassroom (Classroom classroom);

    List<Classroom> fetchAllClassrooms();


    Classroom updateClassroomById(String id_classroom, Classroom classroom);

    void deleteClassroomById(String id_classroom);
    Classroom getClassroomById(String id_classroom);
}
