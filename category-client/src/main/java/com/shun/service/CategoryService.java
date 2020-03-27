package com.shun.service;

import com.shun.entity.Category;

import java.util.Map;

public interface CategoryService {
    Map findByPage(Integer page,Integer rows);

    Map edit(Category category);

    Map add(String name);

    Map del(String[] id);

    Map findByParentId(Integer page,Integer rows,Integer parentId);

    Map addChild(Integer parentId, String name);
}
