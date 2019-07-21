package com.thoughtworks.parking_lot.service.impl;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ParkingLot> getParkingLotsByPage(int page,int pageSize) {
        if(page==0||pageSize==0){ return parkingLotRepository.findAll();}
        if(parkingLotRepository.count()<page*pageSize){
            return parkingLotRepository.findAll().subList((page-1)*pageSize,(int)parkingLotRepository.count());
        }
        return parkingLotRepository.findAll().subList((page-1)*pageSize,page*pageSize);
    }

    @Override
    public ParkingLot getParkingLotById(int id) {
        return parkingLotRepository.findById(id).get();
    }

    @Override
    public ParkingLot updateParkingLot(int id,ParkingLot parkingLot) {
        ParkingLot updateParkingLot=parkingLotRepository.findById(id).orElse(null);
        if(updateParkingLot!=null) {
            updateParkingLot.setCapacity(parkingLot.getCapacity());
            parkingLotRepository.save(updateParkingLot);
            return updateParkingLot;
        }
        return null;
    }


}
