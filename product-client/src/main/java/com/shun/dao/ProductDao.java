package com.shun.dao;

import com.shun.entity.Product;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductDao extends Mapper<Product>, DeleteByIdListMapper<Product,Integer> {
    List<Product> findByPage(Integer start,Integer rows);
}
