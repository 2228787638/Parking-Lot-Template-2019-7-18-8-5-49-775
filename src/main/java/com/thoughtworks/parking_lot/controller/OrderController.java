package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.Order;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    public OrderService orderService;

    @ResponseBody
    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(1,order);
    }

    @ResponseBody
    @PutMapping("/orders/{orderId}")
    public Order updateOrder(@PathVariable int id,@RequestBody Order order) {
        return orderService.updateOrder(id,order);
    }
}
