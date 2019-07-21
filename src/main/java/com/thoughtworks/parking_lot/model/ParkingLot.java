package com.thoughtworks.parking_lot.model;

import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name="parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String name;
    @Range(min=0)
    private int capacity;
    private String position;

    public ParkingLot() {
    }

    public ParkingLot(String name, @Range(min = 0) int capacity, String position) {
        this.name = name;
        this.capacity = capacity;
        this.position = position;
    }
    public ParkingLot(int id ,String name, @Range(min = 0) int capacity, String position) {
        this.id=id;
        this.name = name;
        this.capacity = capacity;
        this.position = position;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
