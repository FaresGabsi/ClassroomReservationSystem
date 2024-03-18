package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Classroom;
import com.classroomregistration.classroomservice.repository.ClassroomRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService implements ClassroomServiceInter {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Override
    public Classroom addClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public List<Classroom> fetchAllClassrooms() {
        List<Classroom> allClassrooms = classroomRepository.findAll();
        return allClassrooms;
    }

    @Override
    public Classroom updateClassroomById(String id_classroom, Classroom classroom) {
        return null;
    }

    @Override
    public void deleteClassroomById(String id_classroom) {

    }

    @Override
    public Classroom getClassroomById(String id_classroom) {
   Optional<Classroom> classroom =classroomRepository.findById(id_classroom);
   if(classroom.isPresent()){return  classroom.get();
    }

     return null;}
}
