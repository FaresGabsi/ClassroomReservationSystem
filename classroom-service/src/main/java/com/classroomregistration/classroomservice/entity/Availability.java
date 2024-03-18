package com.classroomregistration.classroomservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_av;

    @Column(name = "id_classroom")
    private String id_classroom;
    private boolean s1;
    private boolean s2;
    private boolean s3;
    private boolean s4;
    private boolean s5;
    private boolean s6;

    public Availability(Integer id_av, String id_classroom, boolean s1, boolean s2, boolean s3, boolean s4, boolean s5, boolean s6) {
        this.id_av = id_av;
        this.id_classroom = id_classroom;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }

    public Availability() {
    }

    public Integer getId_av() {
        return id_av;
    }

    public void setId_av(Integer id_av) {
        this.id_av = id_av;
    }

    public String getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(String id_classroom) {
        this.id_classroom = id_classroom;
    }

    public boolean isS1() {
        return s1;
    }

    public void setS1(boolean s1) {
        this.s1 = s1;
    }

    public boolean isS2() {
        return s2;
    }

    public void setS2(boolean s2) {
        this.s2 = s2;
    }

    public boolean isS3() {
        return s3;
    }

    public void setS3(boolean s3) {
        this.s3 = s3;
    }

    public boolean isS4() {
        return s4;
    }

    public void setS4(boolean s4) {
        this.s4 = s4;
    }

    public boolean isS5() {
        return s5;
    }

    public void setS5(boolean s5) {
        this.s5 = s5;
    }

    public boolean isS6() {
        return s6;
    }

    public void setS6(boolean s6) {
        this.s6 = s6;
    }
}
