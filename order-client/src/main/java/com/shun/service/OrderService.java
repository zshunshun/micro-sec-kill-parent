package com.shun.service;

import com.shun.entity.Order;

import java.util.Map;

public interface OrderService {
    Map findByPage(Integer page,Integer rows);

    void add(Order order);
}
