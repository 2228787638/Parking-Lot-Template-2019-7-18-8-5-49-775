package com.thoughtworks.parking_lot.service.impl;

import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.OrderService;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ParkingLotService parkingLotService;
    @Test
    public void should_return_a_new_order_when_park_a_car() throws Exception {
        parkingLotService.addPakingLot(new ParkingLot("parkinglot",2,"place"));
        Order order = new Order("parkinglot1","110",new Date(),new Date(),"ok");
        Order reOrder=orderService.addOrder(1,order);
        Assert.assertNotNull(reOrder);
    }

    @Test
    public void should_return_a_updated_order_when_a_car_fetch() throws Exception {
        parkingLotService.addPakingLot(new ParkingLot("parkinglot",2,"place"));
        Order order = new Order("parkinglot1","110",new Date(),new Date(),"ok");
        orderService.addOrder(1,order);
        Order newOrder = new Order("parkinglot1","110",new Date(),new Date(),"close");
        Order reOrder=orderService.updateOrder(1,order);
        Assert.assertEquals("close",reOrder.getStatus());
    }

    @Test
    public void should_return_a_order_message_when_parkinglot_have_no_capacity() throws Exception {
        parkingLotService.addPakingLot(new ParkingLot("parkinglot",0,"place"));
        Order order = new Order("parkinglot1","110",new Date(),new Date(),"ok");
        Order reOrder=orderService.addOrder(1,order);
        Assert.assertNull(reOrder);
    }

}