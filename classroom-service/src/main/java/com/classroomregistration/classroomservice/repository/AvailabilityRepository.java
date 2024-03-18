package com.classroomregistration.classroomservice.repository;

import com.classroomregistration.classroomservice.entity.Availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    @Query("SELECT a FROM Availability a WHERE a.id_classroom = :id_classroom")
    Availability findAvailabilityByIdClassroom(@Param("id_classroom") String id_classroom);

}
