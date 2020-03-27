package com.shun.dao;

import com.shun.entity.Category;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryDao extends Mapper<Category>, DeleteByIdListMapper<Category,Integer> {
}
