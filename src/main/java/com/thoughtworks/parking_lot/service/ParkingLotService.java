package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ParkingLotService {
    ParkingLot addPakingLot(ParkingLot parkingLot);
    ParkingLot deleteParkingLot(int id);
    List<ParkingLot> getParkingLotsByPage(int page,int pageSize);
    ParkingLot getParkingLotById(int id);
    ParkingLot updateParkingLot(int id,ParkingLot parkingLot);
}
