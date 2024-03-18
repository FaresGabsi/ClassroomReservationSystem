package com.classroomregistration.classroomservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    private String id_classroom;
    private Integer capacity;



    private boolean wifi;
    private boolean smart_board;
    private  boolean datashow;
    private Integer power_strip;
    public Classroom() {
    }

    public Classroom(String id_classroom, Integer capacity, boolean wifi, boolean smart_board, boolean datashow, Integer power_strip) {
        this.id_classroom = id_classroom;
        this.capacity = capacity;
        this.wifi = wifi;
        this.smart_board = smart_board;
        this.datashow = datashow;
        this.power_strip = power_strip;
    }

    public String getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(String id_classroom) {
        this.id_classroom = id_classroom;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isSmart_board() {
        return smart_board;
    }

    public void setSmart_board(boolean smart_board) {
        this.smart_board = smart_board;
    }

    public boolean isDatashow() {
        return datashow;
    }

    public void setDatashow(boolean datashow) {
        this.datashow = datashow;
    }

    public Integer getPower_strip() {
        return power_strip;
    }

    public void setPower_strip(Integer power_strip) {
        this.power_strip = power_strip;
    }
}
