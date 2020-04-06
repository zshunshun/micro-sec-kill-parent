package com.shun.dao;

import com.shun.entity.SnapProduct;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SnapProductDao extends Mapper<SnapProduct> {
    List<SnapProduct> findByPage(Integer start,Integer rows);
}
