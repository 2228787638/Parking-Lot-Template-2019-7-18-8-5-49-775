package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    public ParkingLotService parkingLotService;

    @ResponseBody
    @PostMapping("/parkinglots")
    public ParkingLot addParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.addPakingLot(parkingLot);
    }

    @ResponseBody
    @DeleteMapping("/parkinglots/{parkingLotId}")
    public ParkingLot deleteParkingLot(@PathVariable int parkingLotId) {
        return parkingLotService.deleteParkingLot(parkingLotId);
    }
    @ResponseBody
    @GetMapping("/parkinglots")
    public List<ParkingLot> getParkingLots(@RequestParam(value = "page",defaultValue = "0")int page,
                                           @RequestParam(value = "pageSize",defaultValue = "15")int pageSize){
       return parkingLotService.getParkingLotsByPage(page,pageSize);
    }
    @ResponseBody
    @GetMapping("/parkinglots/{parkingLotId}")
    public ParkingLot getParkingLotById(@PathVariable int parkingLotId){
        return parkingLotService.getParkingLotById(parkingLotId);
    }
    @ResponseBody
    @PutMapping("/parkinglots/{parkingLotId}")
    public ParkingLot updateParkingLot(@PathVariable int parkingLotId,@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(parkingLotId,parkingLot);
    }
}
