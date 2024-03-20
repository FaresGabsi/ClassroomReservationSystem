package com.classroomregistration.classroomservice.service;

import com.classroomregistration.classroomservice.entity.Classroom;
import com.classroomregistration.classroomservice.entity.Equipment;
import com.classroomregistration.classroomservice.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ctc.wstx.shaded.msv_core.datatype.xsd.NumberType.save;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Override
    public Classroom getClassroomById(String classroomId) {
        Optional<Classroom> classroom =classroomRepository.findById(classroomId);
        if(classroom.isPresent()){return  classroom.get();
        }

        return null;}


    @Override
    public void deleteClassroomById(String classroomId) {
        Optional<Classroom> classroom =classroomRepository.findById(classroomId);
        if(classroom.isPresent()){classroomRepository.deleteById(classroomId);
        }

    }

    @Override
    public Classroom addClassroom(Classroom classroom) {
        if (classroomRepository.existsById(classroom.getClassroomId())) {
            throw new RuntimeException("Classroom with ID " + classroom.getClassroomId() + " already exists");
        }
        return classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> fetchAllClassrooms() {
        List<Classroom> allClassrooms = classroomRepository.findAll();
        return allClassrooms;
    }

    @Override
    public Classroom updateClassroomById(String classroomId, Classroom updatedClassroom) {
        Optional<Classroom> oldClassroom=classroomRepository.findById(classroomId);
        if(oldClassroom.isPresent()){
            Classroom classroom=oldClassroom.get();
            classroom.setCapacity(updatedClassroom.getCapacity());
            classroom.setEquipments(updatedClassroom.getEquipments());
            return  classroomRepository.save(classroom);
        }else {
            throw new RuntimeException("Classroom with ID " + classroomId + " not found");
        }
    }

    @Override
    public Classroom addEquipmentToClassroom(String classroomId, Equipment equipment) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);
        if (optionalClassroom.isPresent()) {
            Classroom classroom = optionalClassroom.get();
            List<Equipment> equipments = classroom.getEquipments();
            System.out.println(equipment);
            equipments.add(equipment);
            classroom.setEquipments(equipments);
            return classroomRepository.save(classroom);
        } else {
            throw new RuntimeException("Classroom with ID " + classroomId + " not found");
        }

    }

    @Override
    public Classroom removeEquipmentFromClassroom(String classroomId, Equipment equipment) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);
        if (optionalClassroom.isPresent()) {
            Classroom classroom = optionalClassroom.get();
            List<Equipment> equipments = classroom.getEquipments();
            equipments.remove(equipment);
            classroom.setEquipments(equipments);
             return classroomRepository.save(classroom);
        } else {
            throw new RuntimeException("Classroom with ID " + classroomId + " not found");
        }
    }
}
