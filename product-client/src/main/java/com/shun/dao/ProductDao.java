package com.shun.dao;

import com.shun.entity.Product;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductDao extends Mapper<Product>, DeleteByIdListMapper<Product,Integer> {
    List<Product> findByPage(Integer start,Integer rows);
    List<Product> searchByField(String searchField, String searchString, String searchOper, Integer start, Integer rows);
    Integer selectCountByField(String searchField, String searchString, String searchOper, Integer start, Integer rows);
    List<Product> searchByCategoryIds(@Param("ids") List<Integer> ids, int start, Integer rows);
    int selectCountByCategoryIds(@Param("ids") List<Integer> ids, int start, Integer rows);
}
