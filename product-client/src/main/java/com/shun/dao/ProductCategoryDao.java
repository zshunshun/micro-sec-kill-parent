package com.shun.dao;

import com.shun.entity.ProductCategory;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ProductCategoryDao extends Mapper<ProductCategory>, DeleteByIdListMapper<ProductCategory,Integer> {
}
