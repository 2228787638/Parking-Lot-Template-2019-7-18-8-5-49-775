package com.thoughtworks.parking_lot.service.impl;


import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
