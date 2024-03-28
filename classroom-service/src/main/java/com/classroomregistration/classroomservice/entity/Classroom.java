package com.classroomregistration.classroomservice.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public class Classroom {
        @Id
        private String classroomId;
        private Long capacity;
        @ElementCollection
        private List<Equipment> equipments;

    }


