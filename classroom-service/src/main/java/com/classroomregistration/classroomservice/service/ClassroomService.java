package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Classroom;
import com.classroomregistration.classroomservice.entity.Equipment;

import java.util.List;

public interface ClassroomService {
    Classroom getClassroomById(String classroomId);
    void deleteClassroomById(String classroomId);
    Classroom addClassroom (Classroom classroom);

    List<Classroom> fetchAllClassrooms();

    Classroom updateClassroomById(String classroomId, Classroom classroom);
  Classroom addEquipmentToClassroom(String classroomId, Equipment equipment);
    Classroom removeEquipmentFromClassroom(String classroomId, Equipment equipment);

}
