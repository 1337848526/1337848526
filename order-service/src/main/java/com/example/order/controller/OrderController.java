package com.example.order.controller;

import com.example.order.client.UserClient;
import com.example.order.dto.OrderView;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {

    private final UserClient userClient;

    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/orders/{id}")
    public OrderView getOrderById(@PathVariable("id") Long id) {
        UserClient.UserResponse user = userClient.getUserById(id);

        OrderView orderView = new OrderView();
        orderView.setOrderId(id);
        orderView.setUserId(user != null ? user.getId() : id);
        orderView.setUserName(user != null ? user.getName() : null);
        orderView.setStatus("CREATED");
        return orderView;
    }
}
