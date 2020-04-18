package com.shun.service;

import com.shun.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Map findByPage(Integer rows,Integer page);
    Boolean update(Product product);
    Integer insert(Product product);
    void del(Integer[] ids);

    Map searchByField(String searchField, String searchString, String searchOper, Integer page, Integer rows);

    Map searchByCategoryIds(List<Integer> ids, Integer page, Integer rows);

    Map findById(Integer id);
}
