package com.shun.service;

import com.shun.entity.SnapProduct;

import java.util.Date;
import java.util.Map;

public interface SnapProductService {
    Map add(SnapProduct snapProduct);
    Map findAll(Integer rows, Integer page);
    Map findByExample(String searchField, String searchString, String searchOper, Integer page, Integer rows);
    Boolean deleteById(Integer id);
    Boolean update(SnapProduct snapProduct);

    Map searchByTime(Date dateTime,Integer page,Integer rows);

    SnapProduct findById(Integer id);
}
