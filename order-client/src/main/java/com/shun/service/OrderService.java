package com.shun.service;

import java.util.Map;

public interface OrderService {
    Map findByPage(Integer page,Integer rows);
}
