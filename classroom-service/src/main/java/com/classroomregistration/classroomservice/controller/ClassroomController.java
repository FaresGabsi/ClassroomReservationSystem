package com.classroomregistration.classroomservice.controller;

import com.classroomregistration.classroomservice.entity.Classroom;
import com.classroomregistration.classroomservice.entity.Equipment;
import com.classroomregistration.classroomservice.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @GetMapping()
    public List<Classroom> getAllClassrooms() {
        return classroomService.fetchAllClassrooms();
    }
    @GetMapping("/{id}")
    public Classroom getClassroomById(@PathVariable("id") String id){
        return classroomService.getClassroomById(id);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Void> deleteClassroomById(@PathVariable String classroomId) {
        classroomService.deleteClassroomById(classroomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping()
    public ResponseEntity<Classroom> addClassroom(@RequestBody Classroom classroom) {
        try {
            Classroom addedClassroom = classroomService.addClassroom(classroom);
            return new ResponseEntity<>(addedClassroom, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Conflict status for duplicate classroom
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Internal Server Error for other exceptions
        }
    }  @PutMapping("/{classroomId}")
    public ResponseEntity<Classroom> updateClassroomById(@PathVariable String classroomId,
                                                         @RequestBody Classroom updatedClassroom) {
        Classroom classroom = classroomService.updateClassroomById(classroomId, updatedClassroom);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }
    @PostMapping("/{classroomId}/equipments")
    public ResponseEntity<Classroom> addEquipmentToClassroom(@PathVariable String classroomId,
                                                             @RequestBody Equipment equipment) {
        Classroom updatedClassroom = classroomService.addEquipmentToClassroom(classroomId, equipment);
        return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
    }

    @DeleteMapping("/{classroomId}/equipments")
    public ResponseEntity<Classroom> deleteEquipmentFromClassroom(@PathVariable String classroomId,
                                                                  @RequestBody Equipment equipment) {
        Classroom updatedClassroom = classroomService.removeEquipmentFromClassroom(classroomId, equipment);
        return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
    }
}
