package com.thoughtworks.parking_lot.service.impl;


import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Override
    public Order addOrder(int parkingLotId,Order order) {
        if(parkingLotRepository.findById(parkingLotId).get().getCapacity()>0) {
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order updateOrder(int id, Order order) {
        Order oldOrder=orderRepository.findById(id).get();
        oldOrder.setLeaveDate(new Date());
        oldOrder.setStatus("close");
        orderRepository.save(oldOrder);
        return oldOrder;
    }
}
