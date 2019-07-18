package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {

    @Autowired
    public ParkingLotService parkingLotService;

    @ResponseBody
    @PostMapping("/parkingLots")
    public ParkingLot addParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.addPakingLot(parkingLot);
    }


}
