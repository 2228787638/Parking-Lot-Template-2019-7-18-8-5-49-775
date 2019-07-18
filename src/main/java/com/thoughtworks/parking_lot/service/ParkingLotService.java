package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.stereotype.Service;

@Service
public interface ParkingLotService {
    ParkingLot addPakingLot(ParkingLot parkingLot);

}
