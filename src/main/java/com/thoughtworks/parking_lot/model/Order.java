package com.thoughtworks.parking_lot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String parkingLotName;

    private String licensePlate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enterDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date leaveDate;

    private String status;

    public Order(String parkingLotName, String licensePlate, Date enterDate, Date leaveDate, String status) {
        this.parkingLotName = parkingLotName;
        this.licensePlate = licensePlate;
        this.enterDate = enterDate;
        this.leaveDate = leaveDate;
        this.status = status;
    }
}
