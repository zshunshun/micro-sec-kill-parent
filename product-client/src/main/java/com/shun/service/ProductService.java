package com.shun.service;

import com.shun.entity.Product;

import java.util.Map;

public interface ProductService {
    Map findByPage(Integer rows,Integer page);
    Boolean update(Product product);
    Integer insert(Product product);
    void del(Integer[] ids);
}
