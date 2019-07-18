package com.thoughtworks.parking_lot.service.impl;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotServiceImplTest {

    @Autowired
    private ParkingLotService parkingLotService;

    @Test
    public void addPakingLot() {
        ParkingLot parkingLot=parkingLotService.addPakingLot(new ParkingLot("parkinglot1",10,"place1"));

        Assert.assertNotNull(parkingLot);

    }
    @Test
    public void deletePakingLot() {
        ParkingLot parkingLot=parkingLotService.addPakingLot(new ParkingLot("parkinglot1",10,"place1"));
        Assert.assertNotNull(parkingLotService.deleteParkingLot(parkingLot.getId()));

    }

    @Test
    public void getPakingLotsByPage() {
        for(int i=0;i<28;i++){
            ParkingLot parkingLot=parkingLotService.addPakingLot(new ParkingLot("parkinglot"+i,10,"place"+i));
        }
        List<ParkingLot> list=parkingLotService.getParkingLotsByPage(2,15);
        Assert.assertEquals(13,list.size());
    }



}