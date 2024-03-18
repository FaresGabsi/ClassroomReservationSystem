package com.classroomregistration.classroomservice.repository;

import com.classroomregistration.classroomservice.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {

}
