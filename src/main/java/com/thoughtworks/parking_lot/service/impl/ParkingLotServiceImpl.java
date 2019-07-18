package com.thoughtworks.parking_lot.service.impl;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Override
    public ParkingLot addPakingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot deleteParkingLot(int id) {
        ParkingLot parkingLot=parkingLotRepository.findById(id).get();
        if(parkingLot!=null) {
            parkingLotRepository.deleteById(id);
        }
        return parkingLot;
    }


}
